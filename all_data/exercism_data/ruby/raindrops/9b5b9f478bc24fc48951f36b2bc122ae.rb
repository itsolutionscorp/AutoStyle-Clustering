require 'prime'

class Raindrops

  def convert(drops)
    small_primes = find_small_prime_factors(drops)
    small_primes.empty? ? drops.to_s : generate_string(small_primes)
  end

  def find_small_prime_factors(drops)
    Prime.prime_division(drops).flatten.uniq.reject do |value|
      ![3,5,7].include? value
    end
  end

  def generate_string(primes)
    primes.collect do |prime|
      if prime == 3
        "Pling"
      elsif prime == 5
        "Plang"
      else
        "Plong"
      end
    end.join
  end

end
