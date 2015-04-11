ROMAN = [
    ["M", 1000],
    ["CM", 900],
    ["D", 500],
    ["CD", 400],
    ["C",100],
    ["L",50],
    ["XL",40],
    ["X",10],
    ["IX",9],
    ["V",5],
    ["IV",4],
    ["I",1]
  ]

class Fixnum
  def to_roman
    RomanNumeralConverter.new(self).convert
  end
end

class RomanNumeralConverter
  attr_reader :input
  def initialize(input)
    @input = input
  end
  def convert
    num = @input
    result = ""
    while num > 0
      ROMAN.each do |pair|
        if num >= pair[1]
          result << pair[0]
          num -= pair[1]
          break
        end
      end
    end
    result
  end
end

# class Fixnum

#   def to_roman
#     number = self
#     result = ""

#     ROMAN.each do |roman, arabic|
#       while number >= arabic
#         result += roman
#         number -= arabic
#       end
#     end

#     result
#   end

# end
