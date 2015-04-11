class Prime

  def self.nth(n)
    raise ArgumentError.new if n < 1

    primes = [2]
    sums = [4]

    i = 3

    while primes.length < n do
      is_prime = true

      primes.each_with_index do |prime, index|
        while sums[index] < i do
          sums[index] += prime
        end

        if sums[index] == i
          is_prime = false
          break
        end
      end

      if is_prime
        primes << i
        sums << 2 * i
      end

      i = i + 2
    end

    primes.last
  end
end
