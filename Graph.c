//Priya Rajarathinam
//PA5
//prajarat
#include <stdio.h>
#include <stdlib.h>
#include "List.h"
#include "Graph.h"

#define UNDEF 0
#define NIL -1

typedef struct GraphObj
{
    List *neighbour;
    int *color;
    int *parent;
    int *discover;
    int *finish;
    int time;
    int size;
    int order;
} GraphObj;

Graph newGraph(int n)
{
    Graph new = malloc(sizeof(GraphObj));
    new->order = n;
    new->size = 0;
    new->neighbour = calloc(n + 1, sizeof(List));
    new->color = calloc(n + 1, sizeof(int));
    new->parent = calloc(n + 1, sizeof(int));
    new->discover = calloc(n + 1, sizeof(int));
    new->finish = calloc(n + 1, sizeof(int));
    for (int i = 1; i < getOrder(new) + 1; i++)
    {
        new->neighbour[i] = newList();
    }
    for (int i = 1; i < getOrder(new) + 1; i++)
    {
        new->color[i] = 0;
        new->discover[i] =0;
        new->parent[i] = NIL;
        new->finish[i]=0;
    }

    return new;
}

void freeGraph(Graph *pG)
{
    if (*pG != NULL && pG != NULL)
    {
        makeNull(*pG);
        free(*pG);
        *pG = NULL;
    }
}

int getOrder(Graph G)
{
    return G->order;
}

int getSize(Graph G)
{
    return G->size;
}

int getParent(Graph G, int u)
{
    if (1 <= u && u <= getOrder(G))
    {
        return G->parent[u];
    }
    else
    {
        return NIL;
    }
}

int getDiscover(Graph G, int u)
{
    if (1 <= u && u <= getOrder(G))
    {
        return G->discover[u];
    }
}

int getFinish(Graph G, int u)
{
    if (1 <= u && u <= getOrder(G))
    {
        return G->finish[u];
    }
}



 void makeNull(Graph G)
{

    for (int i = 1; i < getOrder(G) + 1; i++)
    {
        freeList(&G->neighbour[i]);
    }
    free(G->neighbour);
    free(G->color);
    free(G->parent);
    free(G->discover);
    free(G->finish);

    G->size = 0;
    G->order = 0;
}
void addEdge(Graph G, int u, int v)
{
    if (1 <= u && u <= getOrder(G) && 1 <= v && v <= getOrder(G))
    {
        addArc(G, u, v);
        addArc(G, v, u);
        G->size--;
    }
}
void addArc(Graph G, int u, int v)
{
    if (1 <= u && u <= getOrder(G) && 1 <= v && v <= getOrder(G))
    {
        if (length(G->neighbour[u]) == 0)
        {
            append(G->neighbour[u], v);
            G->size++;
            return;
        }
        moveFront(G->neighbour[u]);
        int j = 1;
        while (index(G->neighbour[u]) != -1 && j < length(G->neighbour[u]))
        {
            if (v > get(G->neighbour[u]))
            {
                moveNext(G->neighbour[u]);
            }
            j++;
        }

        if (v < front(G->neighbour[u]))
        {
            prepend(G->neighbour[u], v);
        }
        else if (index(G->neighbour[u]) == -1)
        {
            append(G->neighbour[u], v);
        }
        else
        {
            if (v > get(G->neighbour[u]))
            {
                insertAfter(G->neighbour[u], v);
            }
            else
            {
                insertBefore(G->neighbour[u], v);
            }
        }
        G->size++;
    }
}
void DFS(Graph G, List S)
{
    if (length(S) == getOrder(G))
    {
    }
    for (int i = 1; i < getOrder(G) + 1; i++)
    {
        G->color[i] = 0;
        G->parent[i] = NIL;
        G->discover[i]=UNDEF;
        G->finish[i]=UNDEF;
    }
    G->time = 0;
  moveFront(S);
  while(index(S)!=-1){
        if(G->color[get(S)]==0){
            Visit(G,get(S),S);
        }
        moveNext(S);
    }
    for(int i=0;i<getOrder(G);i++){
        deleteBack(S);
    }
}
 void Visit(Graph G, int u, List S)
{

    G->time++;
    G->discover[u] = G->time;
    G->color[u] = 1;
    moveFront(G->neighbour[u]);
    while (index(G->neighbour[u]) != -1)
    {
        if (G->color[get(G->neighbour[u])] == 0)
        {
            G->parent[get(G->neighbour[u])] = u;
            Visit(G, get(G->neighbour[u]), S);
        }
        moveNext(G->neighbour[u]);
    }
    G->color[u] = 2;
    G->time++;
     prepend(S,u);
    G->finish[u] = G->time;
}
Graph transpose(Graph G)
{
    Graph transpose = newGraph(getOrder(G));
    for (int i = 1; i <getOrder(G)+1; i++)
    {
        moveFront(G->neighbour[i]);
        while (index(G->neighbour[i]) != -1)
        {
            addArc(transpose, get(G->neighbour[i]), i);
            moveNext(G->neighbour[i]);
        }
        
    }
    return transpose;
}
Graph copyGraph(Graph G)
{
    Graph new = newGraph(getOrder(G));
    for (int i = 1; i < getOrder(G)+1; i++)
    {
        new->neighbour[i] = copyList(G->neighbour[i]);
        new->color[i] = G->color[i];
        new->discover[i] = G->discover[i];
        new->finish[i] = G->finish[i];
        new->parent[i] = G->parent[i];
    }
    return new;
}



void printGraph(FILE *out, Graph G)
{
    for (int i = 1; i < G->order + 1; i++)
    {
        fprintf(out, "%d: ", i);
        printList(out, G->neighbour[i]);
        fprintf(out, "\n");
    }
}
