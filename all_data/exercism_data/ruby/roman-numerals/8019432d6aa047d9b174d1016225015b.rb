class Fixnum
  def to_roman
    ToRoman.new(self).convert
  end
end

class ToRoman
  MAPPING = {
    1000  =>  "M",
     900  =>  "CM",
     500  =>  "D",
     400  =>  "CD",
     100  =>  "C",
      90  =>  "XC",
      50  =>  "L",
      40  =>  "XL",
      10  =>  "X",
       9  =>  "IX",
       5  =>  "V",
       4  =>  "IV",
       1  =>  "I"
  }

  attr_accessor :arabic
  protected :arabic

  def initialize(arabic)
    @arabic = arabic
  end

  def convert
    MAPPING.each_with_object('') do |(value, symbol), roman|
      while arabic >= value
        roman << symbol
        self.arabic -= value
      end
    end
  end
end
