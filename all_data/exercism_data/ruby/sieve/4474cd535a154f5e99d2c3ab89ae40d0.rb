require 'prime'

class Sieve
  def initialize n
    @n = n
  end
  def primes
    (Prime.first @n).select { |p| p < @n }
  end
end
