#!/usr/bin/env ruby
require_relative 'basic_statistic'

# This silhouette score is computed using same approach with scikit
def silhouette_scikit feature, cluster
  silhouette_point(feature, cluster).sum / feature.length
end

# This silhouette score is computed according to the definition in 
# "Understanding of Internal Clustering Validation Measures" 
def silhouette_paper feature, cluster
  silhouette_cluster(feature, cluster).sum / cluster.length
end

# Construct distance matrix of size n x n
def dist_matrix feature
  n = feature.length
  dmatrix = Array.new(n) { Array.new(n, 0) }
  for i in (0..n-1)
    for j in (i..n-1)
      dmatrix[i][j] = dist(feature[i], feature[j])
      dmatrix[j][i] = dmatrix[i][j]
    end
  end
  return dmatrix
end

# Calculate the distance between all pairs of points and clusters
# Each row represents a point and each column represent a cluster: n x nc
def point_cluster_distance dmatrix, cluster
  n = dmatrix.length
  nc = cluster.length
  pcdmatrix = Array.new(n) { Array.new(nc, 0) }
  for i in (0..n-1) 
    for j in (0..nc-1)
      # pcdmatrix[i] records the distances from point i to all clusters
      pcdmatrix[i][j] = cluster[j].reduce(0) {|sum, ci| sum + dmatrix[i][ci]}
    end
  end
  return pcdmatrix   
end

def silhouette_cluster feature, cluster
  s = silhouette_point feature, cluster
  nc = cluster.length
  sc = Array.new(nc, 0)
  cluster.each_with_index do |subc, i|
    subc.each do |e|
      sc[i] += s[e] 
    end
    sc[i] = sc[i] / cluster[i].length 
  end
  return sc
end

def silhouette_point feature, cluster
  # Compute number of clusters
  n = feature.length
  nc = cluster.length

  dmatrix = dist_matrix feature 
  pcdmatrix = point_cluster_distance dmatrix, cluster

  # bmatrix is created for calculating b
  bmatrix = Array.new(n) {Array.new(nc, 0)}
  for j in (0..nc-1)
    for i in (0..n-1)
      bmatrix[i][j] = pcdmatrix[i][j] / cluster[j].length    
    end
  end

  # Calculate each cluster's silhouette sum
  s = Array.new(n, 0)
  for i in (0..nc-1)
    a_size = cluster[i].length
    cluster[i].each do |j|
      min = bmatrix[j].min
      min_cindex = bmatrix[j].index(min)
      b = min_cindex == i ? bmatrix[j].sort[1] : min 
      a = pcdmatrix[j][i] / (a_size - 1)
      s[j] = (b - a) / [a, b].max
    end
  end
  return s
end
