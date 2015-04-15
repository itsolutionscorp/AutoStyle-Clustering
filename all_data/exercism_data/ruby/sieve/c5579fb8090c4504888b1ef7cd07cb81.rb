class Sieve
  def initialize(limit)
    @limit = limit
  end

  def primes
    [*2..@limit].tap do |numbers|
      numbers.each do |current|
        numbers.reject! { |n| n > current && n % current == 0 }
      end
    end
  end
end
