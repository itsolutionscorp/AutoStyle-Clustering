require "prime"

class Raindrops
  DICT = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
  def self.convert(num)
    primes = to_prime num
    
    if primes.empty?
      num.to_s if primes.empty?
    else
      primes.inject('') { |str, key| str + DICT[key]}
    end
  end

  private

  def self.to_prime(num)
    # find prime numbers and intersections with required numbers 
    Prime.prime_division(num).flatten.sort & DICT.keys
  end
end
