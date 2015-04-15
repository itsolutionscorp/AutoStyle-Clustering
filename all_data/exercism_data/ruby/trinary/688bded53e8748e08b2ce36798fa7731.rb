#!/usr/bin/env ruby

# Exercism 24
# Trinary

class Trinary

  def initialize(string)
    @trinary = string
    @dec_num = 0
  end


  def to_decimal
    (0..@trinary.length).each { |x| @dec_num += @trinary.reverse[x].to_i*(3**x) }
    @trinary.to_i == 0 ? 0 : @dec_num
  end


end
