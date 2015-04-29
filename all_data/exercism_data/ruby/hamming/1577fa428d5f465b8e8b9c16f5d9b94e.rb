#!/usr/bin/env ruby -wKU
# Hamming.rb

# This class has one method for taking two strings which should represent a strand of DNA
# and comparing them to determine the Hamming Distance.
# http://en.wikipedia.org/wiki/Hamming_distance

class Hamming
  class << self
    def compute(strand1, strand2)
      distance = 0
      # Determine which string is longer, if equal we'll use strand1
      test_size = strand1.length <= strand2.length ? strand1.length : strand2.length
      (0...test_size).each do |i|
        unless strand1[i] == strand2[i]
          distance += 1
        end
      end
      return distance
    end
  end

end
