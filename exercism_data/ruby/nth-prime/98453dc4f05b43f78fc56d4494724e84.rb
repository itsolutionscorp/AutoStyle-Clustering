class Prime

  @@primes = [2]

  def self.nth(nth)
    raise ArgumentError if nth < 1
    return 2 if nth == 1
    number = 1
    if @@primes.length > nth
      number = @@primes[nth-1]
    else
      number = @@primes.last > 2 ? @@primes.last : 1
      while @@primes.length < nth
        number += 2
        @@primes.push(number) if prime?(number)
      end
    end
    number
  end

  def self.prime?(number)
    prime = true
    sqrt_number = (number**0.5).to_i
    smaller_primes = @@primes.select {|p| p if p <= sqrt_number}
    unless smaller_primes.empty?
      smaller_primes.each do |n|
        if number%n == 0
          prime = false
          break
        end
      end
    end
    prime
  end

end
