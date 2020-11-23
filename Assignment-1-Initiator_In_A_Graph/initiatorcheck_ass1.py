no_OfNodes = int(input('Enter the no of nodes:  '))
front = -1
queue = []


def enqueue(x):
    global front, queue
    if front == -1:
        front = 0
    queue.append(x)


def dequeue():
    global front, no_OfNodes
    front += 1
    if front <= no_OfNodes - 1 and front < len(queue):
        bfs(queue[front])


def bfs(x):
    global graph, no_OfNodes
    if x not in queue:
        enqueue(x)

    for i in range(no_OfNodes):
        if graph[x][i] and i not in queue:
            enqueue(i)

    dequeue()


graph = [[0 for i in range(no_OfNodes)] for j in range(no_OfNodes)]
vertices = input("enter the name of vertices:    ")
vertices = vertices.replace(" ", "")
while True:
    print("enter the directed connected vertices")
    v1 = (input())[0]
    v2 = (input())[0]
    graph[vertices.index(v1)][vertices.index(v2)] = 1

    resp = (input("More connected vertices?(Y or y / N or n):  "))[0]
    if resp == 'n' or resp == 'N':
        break
print(graph)
start = (input('Enter start vertex:  '))[0]
bfs(vertices.index(start))

if len(queue) == no_OfNodes:
    print(start + '  Can be an initiator')
else:
    print(start + '  Cannot be an initiator')
