require 'prime' # is this cheating?

def get_prime_factors(n)
  Prime.prime_division(n).flat_map do |factor, power|
    [factor] * power
  end
end

class Raindrops
  def self.convert(number)
    result = ""
    prime_factors = get_prime_factors number
    result += 'Pling' if prime_factors.include? 3
    result += 'Plang' if prime_factors.include? 5
    result += 'Plong' if prime_factors.include? 7
    result.empty? ? number.to_s : result
  end
end
