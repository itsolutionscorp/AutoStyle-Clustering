#!/usr/bin/env ruby
# encoding: utf-8
# Factores primos de un numero dado
class Raindrops
  def convert(n)
    return n.to_s if n == 1
    factores = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
    factores_primos = primos(n)
    salida = ''
    factores.each do |key, value|
      factores_primos.include?(key) ? salida = salida + factores[key] : salida
    end
    salida == '' ? salida = n.to_s : salida
  end

  def primos(n)
    factores_primos = []
    loop do
      factor = (2..n).find { |x| n % x == 0 }
      factores_primos.push(factor)
      n /= factor
      break unless n != 1
    end
    factores_primos
  end
end
