require 'prime'

class Sieve
  def initialize(upper_number)
    @upper_number = upper_number
  end

  def primes
    (2..@upper_number).to_a.select! { |int| Prime.prime?(int) }
  end
end
