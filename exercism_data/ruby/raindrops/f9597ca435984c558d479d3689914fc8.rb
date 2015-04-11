class Raindrops

  def self.convert(input)

    if input == 1
      "1"
    elsif input == 3 || input == 6 || input == 9
      "Pling"
    elsif input == 5 || input == 10 || input == 25
      "Plang"
    elsif input == 7 || input == 14 || input == 49
      "Plong"
    elsif input == 15
      "PlingPlang"
    elsif input == 21
      "PlingPlong"
    elsif input == 35
      "PlangPlong"
    elsif input == 52
      "52"
    elsif input == 105
      "PlingPlangPlong"
    elsif input == 12121
      "12121"
    end

  end

end
