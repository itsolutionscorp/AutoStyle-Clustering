class Raindrops
  def self.convert(number)
    rain_speak = add_words(number)

    if rain_speak.empty?
      rain_speak << number.to_s
    end

    rain_speak
  end

  def self.add_words(number)
    raindrop_speak.inject("") do |accumulator, speak|
      if number % speak.first == 0
        accumulator << speak.last
      else
        accumulator
      end
    end
  end

  def self.raindrop_speak
    {3 => "Pling", 5 => "Plang", 7 => "Plong"}
  end
end
