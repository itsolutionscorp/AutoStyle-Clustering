#!/usr/bin/env ruby

class Hamming
  def self.compute(*strands)
    strands.map{|s| s.split('')}.transpose.select{|a,b| a != b}.length
  end
end
