class Raindrops
  def self.prime_to_sound
    {3 => "Pling",
     5 => "Plang",
     7 => "Plong"}
  end

  def self.convert number
    sound = ""

    prime_to_sound.each_pair do |prime, s|
      sound << s if number % prime == 0
    end

    sound.empty? ? number.to_s : sound
  end
end
