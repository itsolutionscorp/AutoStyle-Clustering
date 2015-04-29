class Raindrops
  def self.convert(number)
    factors = (1..number).select { |n| (number % n).zero? }
    i = ""
    end
    if factors.include?(3)
      i += "Pling"
      end
    if factors.include?(5)
      i += "Plang"
      end
    if factors.include?(7)
      i += "Plong"
    end
    if number.prime == false
      i += number.to_s
    end
      i
  end

#
#if number % 3 != 0 && number % 5 != 0 && number % 7 != 0
#  i += number.to_s
