class Sieve
  def initialize(limit)
    @list = (2..limit).to_a
  end

  def primes
    primes = []
    until @list.empty?
      primes << @list.first
      primes.each do |prime|
        @list.reject! { |num| num % prime == 0 }
      end
      @list
    end
    primes
  end
end
