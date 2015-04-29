# coding: utf-8

class Raindrops

  def self.convert(number)
    output = ""
    output << "Pling" if number.modulo(3).zero?
    output << "Plang" if number.modulo(5).zero?
    output << "Plong" if number.modulo(7).zero?
    output << String(number) if output.empty?
    output
  end

end
