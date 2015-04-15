class Prime

  def self.nth(num)
    throw ArgumentError if num <= 0

    primes = 0.upto(num * 100).to_a
    (2..primes.size).each do |i|
      if primes[i]
        not_prime = i * 2
        while not_prime < primes.size
          primes[not_prime] = nil
          not_prime += i
        end
        num -= 1
        return i if num == 0
      end
    end
  end

end
