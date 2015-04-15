class Raindrops
  def self.convert(int)
    case
      when int%3 == 0 && int%5 == 0 && int%7 == 0
        "PlingPlangPlong"
      when int%3 == 0 && int%5 == 0
        "PlingPlang"
      when int%3 == 0 && int%7 == 0
        "PlingPlong"
      when int%5 == 0 && int%7 == 0
        "PlangPlong"
      when int%3 == 0
        "Pling"
      when int%5 == 0
        "Plang"
      when int%7 == 0
        "Plong"
      else
        int.to_s
    end
  end
end
