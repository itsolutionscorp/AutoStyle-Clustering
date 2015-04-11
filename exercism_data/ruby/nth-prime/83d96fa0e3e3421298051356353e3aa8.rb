class Prime
  @primes = [2]

  def self.nth(number)
    raise ArgumentError if number == 0
    current_number = @primes.last

    until @primes[number - 1]
      current_number += 1
      @primes << current_number if self.prime?(current_number)
    end

    @primes[number - 1]
  end

  def self.prime?(number)
    !(2..(Math.sqrt(number).round)).any? { |x| number % x == 0 }
  end
end
