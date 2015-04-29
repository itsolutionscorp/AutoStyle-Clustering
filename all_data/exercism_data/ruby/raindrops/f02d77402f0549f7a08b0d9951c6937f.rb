require 'prime'

class Raindrops

  def self.convert(num)
    array = []
    array << 'Pling' if contains_prime_factor?(num, 3)
    array << 'Plang' if contains_prime_factor?(num, 5)
    array << 'Plong' if contains_prime_factor?(num, 7)
    array << num.to_s if array.empty?
    array.join
  end

  def self.contains_prime_factor?(num, factor_of)
    Prime.prime_division(num).flatten.include?(factor_of)
  end
end
