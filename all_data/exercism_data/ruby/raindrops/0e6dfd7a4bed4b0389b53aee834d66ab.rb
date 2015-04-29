class Raindrops

  def self.convert(number)
    result = self.convert_to_sounds(number)
    if result.empty?
      number.to_s
    else 
      result
    end
  end

  def self.convert_to_sounds(number)
    sounds = ""
    sounds += self.make_sound_if_prime_factor(number, 3, "Pling")
    sounds += self.make_sound_if_prime_factor(number, 5, "Plang")
    sounds += self.make_sound_if_prime_factor(number, 7, "Plong")
  end

  def self.make_sound_if_prime_factor(number, factor, sound)
    self.a_prime_factor?(number, factor) ? sound : ""
  end

  def self.a_prime_factor?(number, factor)
    number % factor == 0
  end

end
