require 'prime'

class Raindrops

  def self.convert(num)
    array = []
    array << 'Pling' if is_prime_factor?(num, 3)
    array << 'Plang' if is_prime_factor?(num, 5)
    array << 'Plong' if is_prime_factor?(num, 7)
    array << num.to_s if array.empty?
    array.join
  end

  
  def self.is_prime_factor?(num, factor_of)
    Prime.prime_division(num).flatten.include?(factor_of)

  end

  end
