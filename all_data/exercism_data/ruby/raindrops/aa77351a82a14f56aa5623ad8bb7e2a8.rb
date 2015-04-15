require 'pry'

module Raindrops

  MAGIC_NUMBERS = [3,5,7]

  def self.convert(int)
    divisors = filter_divisors(int, MAGIC_NUMBERS)
    return int.to_s unless divisors.any?
    str = ""
    divisors.each do |divisor|
      str += 'Pling' if divisor == 3
      str += 'Plang' if divisor == 5
      str += 'Plong' if divisor == 7
    end
    str
  end

  private

  def self.filter_divisors(int, magic_numbers)
    magic_numbers.select{ |divisor| int % divisor == 0 }
  end

end
