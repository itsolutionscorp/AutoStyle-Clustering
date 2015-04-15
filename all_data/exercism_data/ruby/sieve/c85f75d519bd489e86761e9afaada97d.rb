require 'set'

class Sieve
  attr_reader :primes
  def initialize(limit)
    @place = 2
    @limit = limit
    @sieve = Set.new
    @primes = []
    fill_sieve
  end

  private

  def fill_sieve
    until @place >= @limit
      add_prime
      add_multiples
      next_prime
    end
  end

  def add_prime
    @primes << @place
  end

  def add_multiples
    (2..@limit).each do |i|
      break if @place * i > @limit
      @sieve << @place * i
    end
  end

  def next_prime
    @place += 1
    until @sieve.include?(@place) == false
      @place += 1
    end
  end
end
