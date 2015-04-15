class Raindrops
  SOUNDS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(drop)
    return_str = ""
    [3, 5, 7].each do |num|
      if drop % num == 0
        return_str << SOUNDS[num]
      end
    end
    return_str.empty? ? drop.to_s : return_str
  end
end
