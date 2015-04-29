module Prime

  @@primes = [2, 3]

  def self.nth nb
    raise ArgumentError unless nb > 0

    (@@primes.last..150_000).step(2) do |candidate|
      break if @@primes.size > nb
      @@primes << candidate if @@primes.all? { |i| candidate % i != 0}
    end
    @@primes[nb-1]
  end

end
