class Fixnum
  def prime?
    self != 1 && !(2..Math.sqrt(self)).any?{ |d| self % d == 0 }
  end
end

class Sieve
  def initialize(limit)
    @limit = limit
  end

  def primes
    (2..@limit).select { |d| d.prime? }
  end
end
