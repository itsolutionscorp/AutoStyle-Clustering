class Raindrops

  def convert(number)
    rain_speak = "" 

    add_words(rain_speak, number)

    if rain_speak.empty?
      rain_speak << number.to_s
    end

    rain_speak
  end

  private 

  def add_words(rain_speak, number)
    raindrop_speak.each do |prime_factor, speak|
      if number % prime_factor == 0
        rain_speak << speak
      end
    end
  end

  def raindrop_speak
    {3 => "Pling", 5 => "Plang", 7 => "Plong"}
  end

end
