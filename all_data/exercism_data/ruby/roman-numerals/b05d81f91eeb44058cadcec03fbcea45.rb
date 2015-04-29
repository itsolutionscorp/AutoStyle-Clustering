class Fixnum

  ROMAN = {
    1000 => 'M',
    900 => "CM",
    500 => 'D',
    400 => "CD",
    100 => 'C',
    90 => "XC",
    50 => 'L',
    40 => "XL",
    10 => 'X',
    9 => "IX",
    5 => 'V',
    4 => "IV",
    1 => 'I'
  }

  def to_roman
    arabic_number = self
    complete_roman = ""
      ROMAN.each do |n, roman|      
        while arabic_number >= n
          complete_roman << roman
          arabic_number = arabic_number - n
        end
      end
    complete_roman
  end
end
