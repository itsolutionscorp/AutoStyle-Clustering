class Prime
  def self.nth(prime_index)
    raise ArgumentError, "Argument must be greater than 0" if prime_index <= 0
    search_cycle(prime_index)
  end

  def self.search_cycle(prime_index)
    primes = nil
    counter = 2

    loop do
      primes = generate_primes(prime_index * counter)
      counter += 1
      break if primes.count >= prime_index
    end

    primes[prime_index - 1]
  end

  def self.generate_primes(limit)
    primes_list = (2..limit).to_a
    denominators = (2..Math.sqrt(limit))

    denominators.each do |denominator|
      primes_list.delete_if do |numerator|
        if numerator == denominator
          false
        elsif numerator % denominator == 0
          true
        end
      end
    end

    primes_list
  end

  private_class_method :search_cycle, :generate_primes
end
