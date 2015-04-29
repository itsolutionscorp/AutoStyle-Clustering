class Raindrops

  def self.convert(n)
    new.convert(n)
  end

  def convert(n)
    sounds = sounds(n)
    sounds.empty? ? n.to_s : sounds
  end

  private

  def sounds(n)
    factor_sounds.each_with_object("") { |(factor, sound), sounds|
      sounds << sound if n % factor == 0
    }
  end

  def factor_sounds
    { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
  end

end
