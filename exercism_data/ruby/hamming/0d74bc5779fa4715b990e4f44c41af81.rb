# encoding: utf-8

# Hamming distance calculator
module Hamming
  def self.compute(str1, str2)
    shorter_length = [str1.length, str2.length].min

    (0...shorter_length).reduce(0) do |d, i|
      str1[i] == str2[i] ? d : d + 1
    end
  end
end
