require "pry"
require 'ostruct'
class Fixnum

  INDEX = { 1 => "I",
            4 => "IV",
            5 => "V",
            9 => "IX",
            10 => "X",
            40 => "XL",
            50 => "L",
            90 => "XC",
            100 => "C",
            400 => "CD",
            500 => "D",
            900 => "CM",
            1000 => "M"
  }

  def to_roman
    target = self
    array = []
    while target >= 1 do
      array << largest(target)
      target -= largest(target)
    end
    array.map { |i| INDEX[i] }.join
  end

  private

    def largest(num)
      INDEX.keys.select{ |i| i <= num }.last
    end

end
