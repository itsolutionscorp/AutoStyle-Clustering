class Sieve
  attr_reader :primes

  def initialize(size)
    @sieve = [nil, nil] + (2..size).to_a

    @sieve.each do |e|
      next if e.nil?
      (e*2..size).step(e).each do |i|
        @sieve[i] = nil
      end
    end

    @primes = @sieve.compact
  end
end
