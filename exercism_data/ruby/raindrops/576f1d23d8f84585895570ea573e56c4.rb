class  Raindrops

  def self.convert(i)
    case
    when i == 1
      "1"
    when i % 3 == 0 && i % 5 == 0 && i % 7 == 0
      "PlingPlangPlong"
    when i % 3 == 0 && i % 5 == 0
      "PlingPlang"
    when i % 3 == 0 && i % 7 == 0
      "PlingPlong"
    when i % 5 == 0 && i % 7 == 0
      "PlangPlong"
    when i % 3 == 0
      "Pling"
    when i % 5 == 0
      "Plang"
    when i % 7 == 0
      "Plong"
    else
      i.to_s
    end
  end

end
