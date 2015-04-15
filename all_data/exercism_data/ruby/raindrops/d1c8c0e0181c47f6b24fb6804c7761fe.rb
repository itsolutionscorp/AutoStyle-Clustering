class Raindrops
  def self.convert(int)
    raindrop_str = create_raindrop_str(int)
    if raindrop_str == ""
      return int.to_s
    end
    raindrop_str
  end

  private

  def self.create_raindrop_str(int)
    raindrop_str = ""
    if int % 3 == 0
      raindrop_str << "Pling"
    end
    if int % 5 == 0
      raindrop_str << "Plang"
    end
    if int % 7 == 0
      raindrop_str << "Plong"
    end
    raindrop_str
  end
end
