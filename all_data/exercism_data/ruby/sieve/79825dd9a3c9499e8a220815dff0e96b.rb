require 'prime'
class Sieve
  def initialize val
    @val = val
  end
  def primes
    (0..@val).select {|v| Prime.prime? v }
  end
end
