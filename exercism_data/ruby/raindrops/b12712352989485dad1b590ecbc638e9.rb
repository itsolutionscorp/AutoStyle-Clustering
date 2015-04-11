class Raindrops
  RAINDROP_SPEAK = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong",
  }

  def self.convert(number)
    rain_speak = add_words(number)

    if rain_speak.empty?
      rain_speak << number.to_s
    end

    rain_speak
  end

  def self.add_words(number)
    RAINDROP_SPEAK.inject("") do |accumulator, (factor, word)|
      if number % factor == 0
        accumulator << word
      else
        accumulator
      end
    end
  end

  private_class_method :add_words
end
