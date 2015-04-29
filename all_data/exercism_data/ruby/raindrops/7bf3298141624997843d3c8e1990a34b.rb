class Raindrops

  def convert(number)
    rain_speak = []
    raindrop_speak.keys.each do |prime_factor|
      if number % prime_factor == 0
        rain_speak << raindrop_speak[prime_factor]
      end
    end

    output(rain_speak, number)
  end

  def raindrop_speak
    {3 =>  "Pling",5 => "Plang",7 => "Plong"}
  end

  def output(speak, number)
    if speak.empty?
      number.to_s
    else
      speak.join
    end
  end

end
