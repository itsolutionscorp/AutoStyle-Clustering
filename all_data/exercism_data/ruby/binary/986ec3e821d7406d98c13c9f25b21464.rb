#!/usr/bin/env ruby

# Exercism 15
# Binary string to decimal number conversion

class Binary

  def initialize(string)
    @binary = string
    @dec_num = 0
  end

  def to_decimal
    (0..@binary.length).each { |x| @dec_num += @binary.reverse[x].to_i*(2**x) }
    @binary.to_i == 0 ? 0 : @dec_num
  end

end
