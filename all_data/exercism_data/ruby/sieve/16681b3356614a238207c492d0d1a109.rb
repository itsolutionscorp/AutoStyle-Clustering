require 'prime'

class Sieve
  attr_reader :limit
  def initialize(limit)
    @limit = limit
  end

  def primes
    Prime.each(limit).to_a
  end
end
