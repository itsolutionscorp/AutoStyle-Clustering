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
    RAINDROP_SPEAK.select do |factor|
      number % factor == 0
    end.values.join
  end

  private_class_method :add_words
end
