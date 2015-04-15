# sieve.rb
class Sieve
  attr_reader :limit, :sieve

  def initialize(n)
    @limit = n
    @sieve = (2..limit).to_a.map { |i| MarkableNumber.new(i) }
  end

  def primes
    sieve.each_with_object([]) do |n, primes|
      next if n.marked?
      primes << (value = n.value)
      ((value * 2)..limit).step(value) do |multiple|
        sieve[multiple - 2].marked = true
      end
    end
  end
end

class MarkableNumber
  attr_accessor :value,  :marked
  alias_method :marked?, :marked

  def initialize(value)
    @value = value
    @marked = false
  end
end
