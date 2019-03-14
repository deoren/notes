# Docker: Services

- Every service gets a name
- Names are registered with Swarm DNS
- Every service uses Swarm DNS

- Ports mapped to "host" are mapped on every Swarm node
  - e.g., `docker service create -d --name web --network skynet --replicas 1 -p 8080:80`
    - this creates a mapping on every node from 8080 to the container running
      on port 80, regardless of which node the container is actually running
      on

## References

- [Docker Deep Dive by Nigel Poulton, Pluralsight.com](https://www.pluralsight.com/courses/docker-deep-dive-update)
