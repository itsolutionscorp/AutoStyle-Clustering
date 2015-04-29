# sieve.rb
class Sieve
  def initialize(n)
    @n = n
    @p = 2
    @sieve = make_sieve
  end

  def make_sieve
    (2..@n).to_a.map { |i| MarkableNumber.new(i) }
  end

  def primes
    until next_unmarked.nil?
      (2..(@n / @p)).each do |i|
        @sieve[@p * i - 2].mark
      end
      @p = next_unmarked.value
    end
    @sieve.reject(&:marked).map(&:value)
  end

  def next_unmarked
    @sieve.find { |n| n.value > @p && !n.marked? }
  end
end

class MarkableNumber
  attr_accessor :value,  :marked
  alias_method :marked?, :marked

  def initialize(value)
    @value = value
    @marked = false
  end

  def mark
    @marked = true
  end
end
