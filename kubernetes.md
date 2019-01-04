# Kubernetes

## Scratch notes

```
kubectl run mysql --image=mysql:5.5 --env MYSQL_ROOT_PASSWORD=root
kubectl expose deployment mysql --port 3306
kubectl run wordpress --image=wordpress --env WORDPRESS_DB_HOST=mysql --env WORDPRESS_DB_PASSWORD=root
kubectl expose deployment wordpress --port 80 --type LoadBalancer
```

Used with minikube.
