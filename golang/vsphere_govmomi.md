<!-- omit in toc -->
# vSphere / govmomi

<!-- omit in toc -->
## Table of contents

- [About](#about)
- [Types](#types)
- [page 28](#page-28)
- [page 64](#page-64)
- [page 65](#page-65)
- [page 67](#page-67)
- [page 75](#page-75)
- [page 81](#page-81)
- [page 91](#page-91)
- [page 92](#page-92)

## About

Scratch notes recorded while reading through:

- [vSphere Web Services API](https://code.vmware.com/apis/1067/vsphere)
- [govmomi GoDocs](https://pkg.go.dev/github.com/vmware/govmomi)

## Types

- `func (*find.Finder).VirtualMachine(ctx context.Context, path string) (*object.VirtualMachine, error)`

Server: Managed Object
Client: Managed Object *Reference*

finder type seems to have methods for actions
views seem to have methods for data retrieval

Each managed object reference identifies a specific managed object on the
server with its type and a value.

To obtain information about the virtual infrastructure, you retrieve managed
object properties.

With a reference to a managed object, you can obtain information about the
state of the server-side inventory objects and populate client-side data
objects based on the values. You can use one of the following approaches:

- **accessor (getter) methods**
  - client proxy interface provides accessor methods for each data object
    property
- **PropertyCollector**
  - obtain values from specific properties
- **SearchIndex managed object**
  - accepts inventory path, IP address or DNS name as search value
  - returns managed object references to specific managed entities
    - ComputeResource
    - Datacenter
    - Folder
    - HostSystems
    - ResourcePool
    - VirtualMachine

vSphere Data objects can include properties that are defined as composite data
types, such as data objects. The embedded data objects can also contain
properties that are data objects. Properties can nest to several levels.

## page 28

If you retrieve a data object that has an optional property that is unset, the
Server will not return a value for the optional property.

## page 64

The PropertyCollector service interface provides a way to monitor and retrieve
information about managed objects, such as whether a virtual machine is
powered on or whether a host in a cluster is offline.

The PropertyCollector uses one or more filters to determine the scope of
collection and it has methods to retrieve data. A filter uses a set of data
objects that specify the following information:

- Starting point for inventory traversal during the collection operation.
- Inventory traversal path.
- Objects and properties from which data will be collected.

## page 65

A vSphere server creates a default PropertyCollector for every session, and
allows you to create multiple, additional PropertyCollector objects. Create
additional PropertyCollector objects, using one per thread, to perform
mutually independent collection operations.

...

PropertyCollector filter properties identify object properties and paths that
define inventory traversal. For example, you can retrieve the properties for a
VirtualMachine object and specify a traversal path using the
VirtualMachine.network property to obtain the properties for the associated
Network objects.

You can use vSphere view objects (for example, ContainerView) in filters to
simplify traversal specification. A view maintains a subset of inventory
objects, so if there is a change in the inventory hierarchy, you do not have
to recreate the view. Use a view to specify a set of objects that the
PropertyCollector can use for data collection.

*my notes*: I think this is what the above references:

```golang
var vmt []mo.Datastore
err = v.Retrieve(ctx, []string{"VirtualMachine"}, []string{"network"}, &vmt)
if err != nil {
    return nil, err
}
```

**A best practice when using views is to call the DestroyView() method when a
view is no longer needed. This practice frees memory on the server.**

## page 67

To do a single retrieval operation with the PropertyCollector, use the
following steps:

1. Get references to the ViewManager and the PropertyCollector.
1. Create a container view for virtual machines.
1. Create an object specification to define the starting point for inventory navigation.
1. Create a traversal specification to identify the path for the collection.
1. Add the TraversalSpec to the ObjectSpec.selectSet array.
1. Identify the properties to be retrieved.
1. Add the object and property specifications to the property filter specification.
1. Create a list for the filters and add the spec to it.
1. Retrieve the data.
1. Print the virtual machine names.

*my notes*: This seems to be simplified like so:

```golang

  // Create a view of VirtualMachine objects
  m := view.NewManager(c)
  v, err := m.CreateContainerView(
    ctx,
    c.ServiceContent.RootFolder,
    []string{"VirtualMachine"},
    true,
  )
  if err != nil {
    return nil, err
  }

  defer func() {
    if err := v.Destroy(ctx); err != nil {
      fmt.Println("Error occurred while destroying view")
    }
  }()

  var vmt []mo.VirtualMachine
  err = v.Retrieve(ctx, []string{"VirtualMachine"}, []string{"name"}, &vmt)
  if err != nil {
    return nil, err
  }
```

## page 75

During inventory traversal, the PropertyCollector applies the PropertySpec
object or objects (PropertyFilterSpec.propSet) to objects. Inventory traversal
begins with the object identified by ObjectSpec.obj and continues by following
TraversalSpec paths. If PropertySpec.type matches the current object type, and
the skip property is false, the PropertyCollector sends the
PropertySpec.pathSet properties to your client.

The filter uses a ContainerView as a starting point. The TraversalSpec for the
ContainerView specifies the view property for access to the view's virtual
machines. The figure shows TraversalSpec objects that extend navigation from a
VirtualMachine object to the associated Network and ResourcePool objects. The
PropertyCollector applies these TraversalSpec objects to each of the
VirtualMachine objects in the view list. The figure also shows the
PropertySpec objects for collecting data from VirtualMachine, Network, and
ResourcePool objects.

*my notes*: <https://code.vmware.com/apis/968> (I forgot where I was going with this)

## page 81

```Java
// Variables of the following types for access to the API methods
// and to the vSphere inventory.
// -- ManagedObjectReference for the ServiceInstance on the Server
// -- VimService for access to the vSphere Web service
// -- VimPortType for access to methods
// -- ServiceContent for access to managed object services
ManagedObjectReference SVC_INST_REF = new ManagedObjectReference();
VimService vimService;
VimPortType vimPort;
ServiceContent serviceContent;
```

## page 91

PropertyCollector Performance

These factors can affect the performance of a PropertyCollector for any given
session:

- Number of objects
- Number of properties
- Density of property data (composite, nested data objects)
- Frequency of changes to the objects and properties on the server
- Depth of traversal (number of properties traversed)

In addition, a vSphere server is affected by the number of PropertyCollector
instances and the number of filters each instance is supporting across all
sessions on the server.

*To minimize PropertyCollector overhead and the amount of network traffic for
your client application, use View objects with the PropertyCollector.*

## page 92

SearchIndex

The SearchIndex managed object provides a set of methods to retrieve
references to managed objects in the vSphere inventory. You can search by
managed objects inventory path, IP address, datastore path, DNS name, and
various other identifying attributes.

For example, if you know the IP address of a virtual machine, you can obtain
its managed object reference by using the SearchIndex.FindByIp method. You can
use SearchIndex to obtain the reference to a server object, and then use that
reference as the starting object for property collection.

- See the sample applications SearchIndex.java and SearchIndex.cs for more
  information about using SearchIndex.
- See the vSphere API Reference for more information about SearchIndex
  methods.
