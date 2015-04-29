require 'prime'

class PrimeFactors
  def self.for num
    new(num).prime_recurse num
  end

  def initialize num
    @num = num
  end

  def prime_recurse num
    prime = Prime.each(num).find { |prime| num % prime == 0 }
    !prime.nil? ? [prime].concat(prime_recurse(num/prime)) : []
  end
end
