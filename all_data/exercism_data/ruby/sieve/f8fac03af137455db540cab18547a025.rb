class Sieve
  def initialize(limit)
    @nums = (2..limit).to_a
    @primes = []
  end

  def primes
    until @nums.empty?
      @primes << @nums.shift
      @nums.delete_if { |e| e % @primes.last == 0 }
    end
    @primes
  end
end
