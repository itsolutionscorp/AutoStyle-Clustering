#!/usr/bin/env ruby

class Hamming
  def self.compute(one, two)
    hamming = 0

    # Find the shorter of the strings
    smallest = [one, two].min_by { |str| str.length }

    # Iterate through the length of the shorter string and test whether both
    # characters match at index i
    smallest.split('').each_index do |i|
      hamming += 1 if one[i] != two[i]
    end

    hamming
  end
end
