# Kubernetes

## Scratch notes

1. `kubectl run mysql --image=mysql:5.5 --env MYSQL_ROOT_PASSWORD=root`
1. `kubectl expose deployment mysql --port 3306`
1. `kubectl run wordpress --image=wordpress --env WORDPRESS_DB_HOST=mysql --env WORDPRESS_DB_PASSWORD=root`
1. `kubectl expose deployment wordpress --port 80 --type LoadBalancer`

Used with minikube.

## Create skeleton manifests

### Pod

`kubectl run foo --image=ghost --restart=Never --dry-run -o yaml`

```yaml
apiVersion: v1
kind: Pod
metadata:
  creationTimestamp: null
  labels:
    run: foo
  name: foo
spec:
  containers:
  - image: ghost
    name: foo
    resources: {}
  dnsPolicy: ClusterFirst
  restartPolicy: Never
status: {}
```

### Deployment

`kubectl run foo --image=ghost --dry-run -o yaml`

```shell
kubectl run --generator=deployment/apps.v1 is DEPRECATED and will be removed in a future version. Use kubectl run --generator=run-pod/v1 or kubectl create instead.
```

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  creationTimestamp: null
  labels:
    run: foo
  name: foo
spec:
  replicas: 1
  selector:
    matchLabels:
      run: foo
  strategy: {}
  template:
    metadata:
      creationTimestamp: null
      labels:
        run: foo
    spec:
      containers:
      - image: ghost
        name: foo
        resources: {}
status: {}
```

### Service

*This seems to assume that you've already created the 'frontend' service*

`kubectl expose deployment frontend --port 80 --dry-run -o yaml`

```yaml
apiVersion: v1
kind: Service
metadata:
  creationTimestamp: null
  labels:
    app: guestbook
    tier: frontend
  name: frontend
spec:
  ports:
  - port: 80
    protocol: TCP
    targetPort: 80
  selector:
    app: guestbook
    tier: frontend
status:
  loadBalancer: {}

```

## References

- <https://github.com/sebgoa/oreilly-kubernetes>
