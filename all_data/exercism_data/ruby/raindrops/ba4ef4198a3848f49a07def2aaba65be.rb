class Raindrops
  RULES = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

  def self.convert(number)
    result = RULES.map do |divisor, sound_effect|
      if number % divisor == 0
        sound_effect
      end
    end.compact.join("")

    if result.empty?
      result = number.to_s
    end

    result
  end

end
