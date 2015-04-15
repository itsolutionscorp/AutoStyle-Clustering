class Sieve
  attr_accessor :n

  def initialize n
    self.n = n
  end

  # cheating :D
  #
  # require 'prime'
  # def primes
  #   Prime::EratosthenesGenerator.new.take_while {|i| i <= n}
  # end

  def primes
    sieve = (0..n).to_a
    sieve[0] = nil
    sieve[1] = nil

    max = Math.sqrt(n).floor

    2.upto max do |i|
      next unless sieve[i]

      (2*i).step(n, i) do |j|
        sieve[j] = nil
      end
    end

    sieve.compact
  end
end
