#!/usr/bin/env ruby

# Exercism 17
# Sum of Multiples

class SumOfMultiples

  def initialize(num1=3,num2=5,num3=0)
    @num1 = num1
    @num2 = num2
    @num3 = num3
  end

  def to(num)
    multiples = []
    (0..(num-1)).each { |x| x%@num1==0 || x%@num2==0 || @num3!=0 && x%@num3==0 ? multiples << x : false }
    multiples.reduce(:+)
  end

  def self.to(num)
    multiples = []
    (0..(num-1)).each { |x| x%3==0 || x%5==0 ? multiples << x : false }
    multiples.reduce(:+)
  end


end
