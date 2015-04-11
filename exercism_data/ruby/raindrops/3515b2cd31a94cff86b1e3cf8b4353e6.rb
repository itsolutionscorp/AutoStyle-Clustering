class Raindrops
  def self.convert(n)
    if n % 3 == 0 && n % 5 == 0 && n % 7 == 0
      "PlingPlangPlong"
    elsif n % 3 == 0 && n % 5 == 0
      "PlingPlang"
    elsif n % 3 == 0 && n % 7 == 0
      "PlingPlong"
    elsif n % 5 == 0 && n % 7 == 0
      "PlangPlong"
    elsif n % 3 == 0
      "Pling"
    elsif n % 5 == 0
      "Plang"
    elsif n % 7 == 0
      "Plong"
    else
      n.to_s
    end
  end
end
