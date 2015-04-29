module Prime

  @@primes = [2]

  def self.nth nb
    raise ArgumentError unless nb > 0

    @@primes.last.upto(150_000) do |candidate|
      @@primes << candidate unless @@primes.any? { |i| candidate % i == 0}
      break if @@primes.size > nb
    end
    @@primes[nb-1]
  end

end
