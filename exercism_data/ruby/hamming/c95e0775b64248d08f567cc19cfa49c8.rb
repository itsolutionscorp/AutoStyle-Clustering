require 'pp'

module Hamming
  class << self

    def compute(a, b)
      # there is no hamming distance between equal strands
      return 0 if a == b

      # pull characters of each strand into arrays
      a_chars, b_chars = a.chars, b.chars

      # ensure strand lengths are equal
      if a_chars.count > b_chars.count
        a_chars = a_chars.first(b_chars.count)
      elsif a_chars.count < b_chars.count
        b_chars = b_chars.first(a_chars.count)
      end
      strands_length = a_chars.count

      # calculate and return distance
      distance = 0
      (0..strands_length).each {|i| distance += 1 if a_chars[i] != b_chars[i] }
      return distance
    end

  end
end
