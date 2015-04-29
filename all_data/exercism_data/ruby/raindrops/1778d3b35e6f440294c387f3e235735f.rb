#!/usr/bin/env ruby
# encoding: utf-8
# Raindrops
class Raindrops
  def convert(number)
    if number == 1
      return '1'
    else
      divisores = prime?(number)
      output = result(divisores)
      if output == ''
        return number.to_s
      end
    end
    output
  end

  # This method return all factors of a number in an ARRAY
  def prime?(number)
    divisores = []
    1.upto(number) do|num|
      if number % num == 0
        divisores.push num
      end
    end
    divisores
  end

  # This method forms the final string based on the factors
  def result(divisores)
    cadena = ''
    divisores.each do |num|
      case num
      when 3
        cadena = cadena.concat('Pling')
      when 5
        cadena = cadena.concat('Plang')
      when 7
        cadena = cadena.concat('Plong')
      end
    end
    cadena
  end
end
