class Sieve
  attr_reader :limit

  def initialize(limit)
    raise "Integer must be greater than or equal to 2" if limit < 2

    @limit = limit
  end

  def primes
    prime_candidates = prime_candidates_with_consecutive_integers

    prime_candidates = eliminate_multiples_from prime_candidates

    return_primes_for prime_candidates
  end

  private

  def start
    2
  end

  def number_of_passes
    Integer(Math.sqrt(limit).floor)
  end

  def prime_candidates_with_consecutive_integers
    candidates = []

    start.upto(limit) do |p|
      candidates[p] = p
    end
    
    candidates
    # => [nil, nil, 2, 3, ...]
  end

  def eliminate_multiples_from prime_candidates
    start.upto(number_of_passes) do |p|
      if prime_candidates[p] != 0
        j = p * p
        while j <= limit
          prime_candidates[j] = 0
          j = j + p
        end
      end
    end

    prime_candidates
  end

  def return_primes_for prime_candidates
    i = 0
    primes = []
    
    start.upto(limit) do |p|
      if prime_candidates[p] != 0
        primes[i] = prime_candidates[p]
        i = i + 1
      end
    end

    primes
  end
end
