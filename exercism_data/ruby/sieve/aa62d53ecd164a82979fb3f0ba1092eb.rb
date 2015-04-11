class Sieve
  attr_reader :max
  def initialize(max)
    @max = max
  end

  def range
    @range ||= (2..max).map { |n| Number.new(n) }
  end

  def primes
    sieve_primes
    range.reject{|n| n.is_not_prime }.map(&:value)
  end

  private

  def sieve_primes
    # use map to modify the range in place
    range.map do |number|
      next if number.is_not_prime
      mark_multiples(number) if number.is_prime?
    end
  end

  def mark_multiples(num)
    multiples(num).map(&:mark_as_not_prime)
  end

  def multiples(num)
    range.select{ |n| n.value % num.value == 0 }
  end

  class Number
    attr_reader :value, :is_not_prime
    def initialize(value)
      @value = value
    end

    def is_prime?
      (2..sq_root).detect { |num| value % num == 0 }
    end

    def sq_root
      Math.sqrt(value)
    end

    def mark_as_not_prime
      @is_not_prime = true
    end
  end
end
