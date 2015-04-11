class Prime
  def self.nth(number)
    raise ArgumentError if number <= 0

    @primes ||= [2]
    return @primes[number-1] if @primes[number-1]

    current = @primes.last + 1
    while(@primes.length < number)
      if @primes.none? { |prime| current % prime == 0 }
        @primes.push(current)
      end
      current += 1
    end
    @primes[number-1]
  end
end
