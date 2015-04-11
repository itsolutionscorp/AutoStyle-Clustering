class Raindrops
  def self.convert(number)
    string = sounds(number)

    string.empty? ? number.to_s : string
  end

  private

  def self.sounds(number)
    raindrop_speak.select {|prime_factor, sound|
      number % prime_factor == 0
    }.values.join
  end

  def self.raindrop_speak
    { 3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong' }
  end
end
