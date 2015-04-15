#class Raindrops
#  def self.convert(number)
#    case
#    when number % 3 == 0 && number % 5 != 0 && number % 7 != 0
#      "Pling"
#    when number % 7 == 0 && number % 5 == 0 && number % 3 != 0
#      "PlangPlong"
#    when number % 5 == 0 && number % 3 != 0 && number & 7 != 0
#      "Plang"
#    when number % 5 == 0 && number % 3 == 0 && number % 7 != 0
#      "PlingPlang"
#    when number % 7 == 0 && number % 3 != 0 && number % 5 != 0
#      "Plong"
#    when number % 7 == 0 && number % 3 == 0 && number % 5 != 0
#      "PlingPlong"
#    when number % 3 == 0 && number % 5 == 0 && number % 7 == 0
#      "PlingPlangPlong"
#    else
#      number == 1
#      number.to_s
#    end
#  end
#end

class Raindrops
  def self.convert(number)
    string = ""
    if number % 3 == 0
      string += "Pling"
    end
    if number % 5 == 0
      string += "Plang"
    end
    if number % 7 == 0
      string += "Plong"
    end
    if number % 3 != 0 && number % 5 != 0 && number % 7 != 0
      string += number.to_s
    end
    string
  end
end
