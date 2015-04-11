#!/usr/bin/env ruby
# encoding: utf-8
# scrabble
class Scrabble
  attr_reader :cadena
  def initialize(cadena)
    @cadena = cadena.to_s.downcase
  end

  def self.score(cadena)
    new(cadena).score
  end

  def score
    suma = 0
    longitud = cadena.length
    (0..longitud - 1).each do |i|
      suma += valores.fetch(cadena[i]) { 0 }
    end
    suma
  end
  # De aqui obtendremos los valores de los caracteres
  def valores
    {
     'a' => 1, 'e' => 1, 'i' => 1, 'o' => 1, 'u' => 1,
     'l' => 1, 'n' => 1, 'r' => 1, 's' => 1, 't' => 1,
     'd' => 2, 'g' => 2,
     'b' => 3, 'c' => 3, 'm' => 3, 'p' => 3,
     'f' => 4, 'h' => 4, 'v' => 4, 'w' => 4, 'y' => 4,
     'k' => 5,
     'j' => 8, 'x' => 8,
     'q' => 10 , 'z' => 10
    }
  end
end
