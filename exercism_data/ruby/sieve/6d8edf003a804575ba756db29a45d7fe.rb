require 'prime'
class Sieve
  def initialize val
    @val = val
  end
  def primes
    (2..@val).select {|v| Prime.prime? v }
  end
end
