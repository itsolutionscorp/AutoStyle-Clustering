class Raindrops
  def self.convert(drops)
    if (drops % 3 == 0) && (drops % 7 == 0) && (drops % 5 == 0)
      "PlingPlangPlong"
    elsif (drops % 3 == 0) && (drops % 7 == 0)
      "PlingPlong"
    elsif (drops % 3 == 0) && (drops % 5 == 0)
      "PlingPlang"
    elsif (drops % 7 == 0) && (drops % 5 == 0)
      "PlangPlong"
    elsif drops % 7 == 0
      "Plong"
    elsif drops % 5 == 0
      "Plang"
    elsif drops % 3 == 0
      "Pling"
    else
      drops.to_s
    end
  end
end
