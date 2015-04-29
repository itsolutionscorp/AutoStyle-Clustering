#!/usr/bin/env ruby

# Exercism 1
# Hamming Test

class Hamming

  def self.compute(str_1, str_2)

    return -1 unless str_1.length == str_2.length

    str_1.length.times.count { |x| str_1[x] != str_2[x] }
    
  end

end
