require 'prime'

class Raindrops
  def self.convert(number)
    prime_factorization = Prime.prime_division(number).flatten
    output = []
    output << "Pling" if prime_factorization.include? 3
    output << "Plang" if prime_factorization.include? 5
    output << "Plong" if prime_factorization.include? 7
    output << number.to_s if output.empty?
    output.join
  end
end
