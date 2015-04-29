class Raindrops
  def self.convert(number)
    rain = fetch_raindrop_sounds(number)

    rain.compact.empty? ? "#{number}" : rain.compact.join
  end

  private

  def self.fetch_raindrop_sounds(number)
    raindrop_speak = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

    raindrop_speak.select {|prime_factor, raindrop_sound| number % prime_factor == 0 }.values
  end
end
