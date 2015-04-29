require 'pry'

class Fixnum
  ROMAN_MAP = {
    1000 =>  'M',
    900  =>  'CM',
    500  =>  'D',
    400  =>  'CD',
    100  =>  'C',
    90   =>  'XC',
    50   =>  'L',
    40   =>  'XL',
    10   =>  'X',
    9    =>  'IX',
    5    =>  'V',
    4    =>  'IV',
    1    =>  'I'
  }
  def to_roman
    left = self
    ROMAN_MAP.reduce('') do |result, (value, roman)|
      break(result) if left == 0
      next(result) if left < value
      complete = left / value
      left = left % value
      result + (roman * complete)
    end
  end
end
