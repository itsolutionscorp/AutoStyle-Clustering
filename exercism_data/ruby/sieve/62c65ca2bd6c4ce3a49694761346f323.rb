class Sieve
  def initialize(limit)
    @range = (2..limit)
  end

  def primes
    numbers = @range.to_a
    prime = []
    begin
      shifted = numbers.shift
      prime << shifted
      numbers.reject! {|i| i % shifted == 0 }
    end until numbers.empty?
    prime
  end
end

# bob = Sieve.new(1000)
# bob.calculate
