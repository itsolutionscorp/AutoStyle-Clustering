class Raindrops
  NOISES = %w(Pling Plang Plong)
  PRIMES = [3, 5, 7]

  def self.convert(rain)
    primes = PRIMES.each
    result = NOISES.select { rain % primes.next == 0 }.join

    result.empty? ? rain.to_s : result
  end
end
