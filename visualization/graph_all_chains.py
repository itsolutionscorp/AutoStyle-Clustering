#!/usr/bin/env python
'''
Created on Dec 30, 2014

@author: joe
'''
from __future__ import division
import style_chain
import networkx as nx


def add_chain_to_graph(chain, graph, sets):
    node = chain.head
    while node:
        score = int(node.flog_score)
        if len(sets) > score:
            sets[score].add('%s'%node.index)
        if node.next:
            graph.add_edge('%s'%node.index, '%s'%node.next.index)
        node = node.next

def main():
    G = nx.DiGraph()
    c = style_chain.generate_chain(0, 1, 1.1, '', style_scores='data/feature/flog_feature.np',  
                   score_names='data/feature/flog_names.np')
    n = len(c.files)
    sets = []
    for i in xrange(50):
        sets.append(set())
    for i in range(50):
        node_name = 'Score: %s'%(i)
        sets[i].add(node_name)
        G.add_node(node_name, {'shape':'none', 'height':'.01'})
        G.add_edge('Score: %s'%(i), 'Score: %s'%(i+1), {'style':'invis'})
    G.add_node('Score: %s'%(50), {'shape':'none', 'height':'.01'})
    for i in xrange(200):
        c = style_chain.generate_chain(i, 1, 1, '', style_scores='data/feature/flog_feature.np',  
                   score_names='data/feature/flog_names.np')
        add_chain_to_graph(c, G, sets)
    A = nx.to_agraph(G)
    A.graph_attr.update(ranksep='.01')
    for s in sets:
        A.add_subgraph(list(s), rank='same')
    A.draw('all_chains.png', prog='dot')
    

if __name__ == '__main__':
    main()