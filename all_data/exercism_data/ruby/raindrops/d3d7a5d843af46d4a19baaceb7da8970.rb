class Raindrops
  def self.convert(number)
    case
    when (number % 3 == 0) && (number % 5 == 0) && (number % 7 == 0)
      "PlingPlangPlong"
    when (number % 3 == 0) && (number % 5 == 0)
      "PlingPlang"
    when (number % 3 == 0) && (number % 7 == 0)
      "PlingPlong"
    when (number % 5 == 0) && (number % 7 == 0)
      "PlangPlong"
    when number % 3 == 0
      "Pling"
    when number % 5 == 0
      "Plang"
    when number % 7 == 0
      "Plong"
    else
      number.to_s
    end
  end
end
