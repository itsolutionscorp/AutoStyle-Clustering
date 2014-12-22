#!/usr/bin/env ruby

def process_cluster_data cluster_data
  cluster_data.zip((0..cluster_data.length-1).to_a).group_by { |array| array[0] }.values.map { |a| a.map { |b| b[1] }}
end

cluster = process_cluster_data(IO.readlines(ARGV[0]).map(&:to_f))
dist_matrix = IO.readlines(ARGV[1]).map { |line| line.split.map(&:to_f) }
indexes = IO.readlines(ARGV[2]).map(&:to_i)
#flog_scores = IO.readlines(ARGV[3]).map
code_number = ARGV[3].to_i

code_index = indexes.index(code_number)

code_cluster = -1

cluster.each_with_index do |subc, i|
  if subc.include? code_index
    code_cluster = i
  end 
end

puts "Error code_cluster" if code_cluster == -1


code_distances = dist_matrix[code_index]
dist_within_cluster = []

cluster[code_cluster].each do |index|
  dist_within_cluster << [code_distances[index], index]
end

closest_index = dist_within_cluster.sort_by(&:first)[1][1]

puts indexes[closest_index]


