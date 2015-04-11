class Raindrops
  def self.convert(number)
    rain_speak = add_words(number)

    if rain_speak.empty?
      rain_speak << number.to_s
    end

    rain_speak
  end

  private_class_method

  def self.add_words(number)
    raindrop_speak.inject("") do |accumulator, (factor, word)|
      if number % factor == 0
        accumulator << word
      else
        accumulator
      end
    end
  end

  def self.raindrop_speak
    {3 => "Pling", 5 => "Plang", 7 => "Plong"}
  end
end
