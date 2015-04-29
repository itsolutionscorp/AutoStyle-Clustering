class Raindrops

  PRIMES = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
  PRIMES.freeze

  def self.convert(number)
    raindrops = ""
    PRIMES.keys.each { |key|  raindrops << PRIMES[key] if number % key == 0 }
    return raindrops.empty? ? number.to_s : raindrops
  end
end
