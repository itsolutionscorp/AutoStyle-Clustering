class Raindrops

  CONVERSION_HASH = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(number)
    raindrop = ""
    CONVERSION_HASH.each do |factor, song|
      if number % factor == 0
        raindrop += song
      end
    end
    raindrop != "" ? raindrop : number.to_s
  end

end
