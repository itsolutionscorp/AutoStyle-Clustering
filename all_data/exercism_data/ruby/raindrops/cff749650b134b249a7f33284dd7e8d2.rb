class Raindrops
  def self.convert(number)
    rain = fetch_raindrops(number)

    rain.compact.empty? ? "#{number}" : rain.compact.join
  end

  private

  def self.fetch_raindrops(number)
    prime_raindrops = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

    prime_raindrops.select {|k, v| number % k == 0 }.values
  end
end
