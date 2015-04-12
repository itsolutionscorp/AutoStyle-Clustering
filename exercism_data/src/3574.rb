#!/usr/bin/env ruby

class Hamming
  def compute(a, b)
    hamming = 0

    # Find the shorter of the strings
    smallest = [a, b].min_by(&:length)

    # Iterate through the length of the shorter string and test whether both
    # characters match at index i
    smallest.split('').each_index do |i|
      hamming += 1 if a[i] != b[i]
    end

    hamming
  end
end
