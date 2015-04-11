#!/usr/bin/env ruby
# encoding: utf-8
# Grains clase
class Grains
  # square metodo
  def square(cuadrado)
    2**(cuadrado - 1)
  end
  # metodo Para total
  def total
    total = 0
    (1..64).each { |num| total += square(num) }
    total
  end
end
