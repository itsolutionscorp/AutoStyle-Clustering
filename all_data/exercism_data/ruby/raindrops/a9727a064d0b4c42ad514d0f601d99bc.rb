class Raindrops

  def convert(number)
    rain_speak = "" 

    raindrop_speak.each do |prime_factor, speak|
      if number % prime_factor == 0
        rain_speak << speak
      end
    end

    rain_speak.empty? ? number.to_s : rain_speak
  end

  def raindrop_speak
    {3 =>  "Pling",5 => "Plang",7 => "Plong"}
  end
end
