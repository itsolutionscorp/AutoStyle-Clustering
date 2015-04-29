class Raindrops
  def self.convert(number)
    case
    when number % 3 == 0 && number % 5 != 0 && number % 7 != 0
      "Pling"
    when number % 7 == 0 && number % 5 == 0 && number % 3 != 0
      "PlangPlong"
    when number % 5 == 0 && number % 3 != 0 && number & 7 != 0
      "Plang"
    when number % 5 == 0 && number % 3 == 0 && number % 7 != 0
      "PlingPlang"
    when number % 7 == 0 && number % 3 != 0 && number % 5 != 0
      "Plong"
    when number % 7 == 0 && number % 3 == 0 && number % 5 != 0
      "PlingPlong"
    when number % 3 == 0 && number % 5 == 0 && number % 7 == 0
      "PlingPlangPlong"
    else
      number == 1
      number.to_s
    end
  end
end
