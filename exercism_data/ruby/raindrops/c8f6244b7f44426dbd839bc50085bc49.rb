require 'prime'

class Raindrops

  def self.convert(number_of_drops)
    list_of_primes = Prime.prime_division(number_of_drops).map{ |x| x[0] }
    return "PlingPlangPlong" if list_of_primes.include? 3 and list_of_primes.include? 5 and list_of_primes.include? 7
    return "PlingPlong" if list_of_primes.include? 3 and list_of_primes.include? 7
    return "PlingPlang" if list_of_primes.include? 3 and list_of_primes.include? 5
    return "PlangPlong" if list_of_primes.include? 5 and list_of_primes.include? 7
    return "Pling" if list_of_primes.include? 3
    return "Plang" if list_of_primes.include? 5
    return "Plong" if list_of_primes.include? 7
    number_of_drops.to_s
  end

end
