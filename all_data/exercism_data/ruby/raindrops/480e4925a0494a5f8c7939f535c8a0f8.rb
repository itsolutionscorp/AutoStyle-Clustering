class Raindrops
  def self.convert(number)
    rain = fetch_raindrops(number)

    rain.compact.empty? ? "#{number}" : rain.compact.join
  end

  private

  def self.fetch_raindrops(number)
    prime_raindrops = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
    primes = prime_raindrops.keys

    primes.map {|p| prime_raindrops[p] if number % p == 0 }
  end
end
