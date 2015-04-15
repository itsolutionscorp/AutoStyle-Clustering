require 'prime'

class Raindrops

  def self.convert(num)
    response = []
    response << "Pling" if Prime.prime_division(num).has_prime_factor?(3)
    response << "Plang" if Prime.prime_division(num).has_prime_factor?(5)
    response << "Plong" if Prime.prime_division(num).has_prime_factor?(7)
    response << num if Prime.prime_division(num).has_prime_factors?(3,5,7)
    response.raindrops
  end

end

class Array
  def has_prime_factor?(num)
    self.flatten.include?(num)
  end

  def has_prime_factors?(*numbers)
    self.flatten.any? { |x| numbers.include?(x) } == false
  end

  def raindrops
    self.join(",").tr(",", "")
  end
end
