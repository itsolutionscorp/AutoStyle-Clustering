class Raindrops
  SOUNDS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong",
  }

  def self.convert(drops)
    sounds = ""
    SOUNDS.each do |number, noise|
      if drops % number == 0
        sounds += noise
      end
    end

    if sounds == ""
      sounds = drops.to_s
    end
    sounds
  end
end
