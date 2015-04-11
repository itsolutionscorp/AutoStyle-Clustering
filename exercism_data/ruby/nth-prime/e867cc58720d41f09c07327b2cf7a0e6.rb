class Prime

  def self.nth(target)
    raise ArgumentError.new 'Expected a positive integer' unless target > 0

    primes.take(target).to_a.last
  end

  private

  def self.natural_numbers
    (1..Float::INFINITY).lazy
  end

  def self.primes
    natural_numbers.select do |number|
      number > 1 && !(2..Math.sqrt(number)).any? { |i| number % i == 0 }
    end
  end
end
