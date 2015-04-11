class Raindrops
  def self.convert(i)
    if i % (3 * 5 * 7) == 0
      "PlingPlangPlong"
    elsif i % 35 == 0
      "PlangPlong"
    elsif i % 21 == 0
      "PlingPlong"
    elsif i % 15 == 0
      "PlingPlang"
    elsif i % 7 == 0
      "Plong"
    elsif i % 5 == 0
      "Plang"
    elsif i % 3 == 0
      "Pling"
    else
      i.to_s
    end
  end
end
