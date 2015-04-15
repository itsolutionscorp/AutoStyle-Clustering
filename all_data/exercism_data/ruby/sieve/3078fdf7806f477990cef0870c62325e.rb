class Sieve
  attr_reader :limit, :candidates, :composites

  def initialize(limit)
    @limit = limit
    @candidates = (2..limit).to_a
    @composites = {}
  end

  def primes
    candidates.each_with_object([]) do |candidate, primes|
      next if composites.key?(candidate)
      primes << candidate
      ((candidate * 2)..limit).step(candidate) do |multiple|
        composites[multiple] = true
      end
    end
  end
end
