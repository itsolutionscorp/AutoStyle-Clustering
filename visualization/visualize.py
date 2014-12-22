#!/usr/bin/env python

import argparse
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.cm as cm
from calc_tsne import calc_tsne
from tsne import tsne_dist_matrix

class ClusterPlotter:

    def __init__(self, features, clusters, source_dir, index):
        self.features=features
        self.clusters=clusters
        self.source_dir=source_dir
        self.index=index

        self.k=np.unique(clusters).shape[0]
        self.colors=cm.rainbow(np.linspace(0,1,self.k))

    def plot_all(self):
        x = self.features[:,0]
        y = self.features[:,1]

        fig = plt.figure()
        fig.suptitle('All clusters together with ' + str(self.clusters.shape[0]) + ' points total')
        ax = fig.add_subplot(1,1,1)

        color_points = np.zeros((x.shape[0], 4))
        for i in range (x.shape[0]):
            color_points[i,:] = self.colors[self.clusters[i]]

        ax.scatter(x, y, color=color_points, picker=True)
        fig.canvas.mpl_connect('pick_event', lambda e: self.onpick(e, 0))

    def plot_individual(self):
        """Precondition: everything is sorted by cluster, 
        i.e. all points in cluster 1 appear before all points in cluster 2"""
        x = self.features[:,0]
        y = self.features[:,1]
        figs = []
        axes = []
        cluster_figs = {}

        for i in range(self.k):
            fig = plt.figure()
            fig.suptitle('Cluster with ' + str(self.clusters[self.clusters==i].shape[0]) + ' points')
            ax = fig.add_subplot(1,1,1)
            figs.append(fig)
            axes.append(ax)
            cluster_figs[i] = [fig, ax, 0]

        offset = 0
        for cluster_num in range(self.k):
            x_cluster = x[self.clusters==cluster_num,:]
            y_cluster = y[self.clusters==cluster_num,:]
            cluster_figs[cluster_num][1].scatter(x_cluster, y_cluster, color=self.colors[cluster_num,:], picker=True)
            cluster_figs[cluster_num][2] = offset
            offset += x_cluster.shape[0]

        for i in range(self.k):

            def build_new_lambda(i):
                return lambda e: self.onpick(e, cluster_figs[i][2])

            pick_function = build_new_lambda(i)
            cluster_figs[i][0].canvas.mpl_connect('pick_event', pick_function)

    def get_source_code(self, i, offset):
        print i
        print offset
        submission_file = str(self.index[i+offset].astype(int))
        print submission_file
        file_path = self.source_dir + '/' + submission_file + '.rb'
        with open(file_path.strip(), 'r') as submission_file:
            return submission_file.read()

    def onpick(self, event, offset):
        print ("####################\n")
        ind = event.ind
        for i in ind:
            print (self.get_source_code(i, offset))
            print ("--------------------\n")

    def show(self):
        plt.show()

def main():
    
    parser = argparse.ArgumentParser(description='Plot clusters.')
    parser.add_argument('data', help='location of file that contains data (features or distance matrix). Should be readable by numpy')
    parser.add_argument('clusters', help='location of file that contains a list of clusters. Each number in the cluster corresponds to a row in the features.')
    parser.add_argument('source', help='location of folder that contains the source code of points to be plotted')
    parser.add_argument('index', help='location of file that maps index in the feature/cluster file to name of source code. As of now, file names here should not include the .rb at the end')
    parser.add_argument('-t', '--tsne', action='store_true', help='include this option to visualize the clusters using tsne')
    parser.add_argument('-i', '--individual-plots', action='store_true', help='include this option to plot each cluster individually in addition to all clusters together')
    parser.add_argument('-d', '--distance-matrix', action='store_true', help='include this option if data is a distance matrix, instead of features. This option should only be included if plotting with tsne')
    args = parser.parse_args()
    data = np.loadtxt(args.data)
    clusters = np.loadtxt(args.clusters)
    source_dir = args.source
    index = np.loadtxt(args.index).astype(int)
    use_tsne = args.tsne
    use_individual_plots = args.individual_plots
    is_distance_matrix = args.distance_matrix

    sort_order = clusters.argsort()
    sorted_data = data[sort_order, :]
    sorted_clusters = clusters[sort_order]
    sorted_index = index[sort_order]

    if is_distance_matrix:
        if use_tsne:
            plotting_data = tsne_dist_matrix(data, perplexity=30)
        else:
            print("You can plot a distance matrix only using tsne.") 
    else:
        if use_tsne:
            plotting_data = calc_tsne(sorted_data, PERPLEX=30)
        else:
            plotting_data = sorted_data

    c = ClusterPlotter(plotting_data, sorted_clusters, source_dir, sorted_index)
    c.plot_all()
    if use_individual_plots:
        c.plot_individual()
    c.show()

if __name__=='__main__':
    main()
