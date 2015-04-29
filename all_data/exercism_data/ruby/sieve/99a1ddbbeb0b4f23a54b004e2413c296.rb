class Sieve

  attr_reader :range
  def initialize(limit)
    @range = (2..limit)
  end

  def primes
    @primes ||= calculate
  end

  private
  def calculate
    numbers = range.to_a
    primes = []
    begin
      num = numbers.shift
      primes << num
      numbers.reject! {|i| i % num == 0 }
    end until numbers.empty?
    primes
  end

end
