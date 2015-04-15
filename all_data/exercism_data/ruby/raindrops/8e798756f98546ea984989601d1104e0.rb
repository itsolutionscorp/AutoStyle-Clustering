require 'prime'

class Raindrops
  def self.convert(num)
    ret_str = ''

    primes = Raindrops.prime_factors(num)

    return num.to_s if (primes - [3,5,7]).size == primes.size # excludes 3,5,7

    ret_str += 'Pling' if primes.include?(3)

    ret_str += 'Plang' if primes.include?(5)

    ret_str += 'Plong' if primes.include?(7)

    return ret_str

  end

  def self.prime_factors(num)
    return [] if num == 1
    Prime.take_while { |p| p <= num }.each do |n|
      return [n] + prime_factors(num / n) if num % n == 0
    end
  end
end
