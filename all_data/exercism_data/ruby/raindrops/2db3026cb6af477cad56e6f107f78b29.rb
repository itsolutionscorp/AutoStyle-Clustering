# I'm sure there's a better way to do this...

class Raindrops

  def self.convert(num)
    if    num % 3 == 0 && num % 5 == 0 && num % 7 == 0
      "PlingPlangPlong"
    elsif num % 3 == 0 && num % 5 == 0
      "PlingPlang"
    elsif num % 3 == 0 && num % 7 == 0
      "PlingPlong"
    elsif num % 5 == 0 && num % 7 == 0
      "PlangPlong"
    elsif num % 3 == 0
      "Pling"
    elsif num % 5 == 0
      "Plang"
    elsif num % 7 == 0
      "Plong"
    else
      num.to_s
    end
  end

end
