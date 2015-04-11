class Numeric
  
  NUMBER_TRANS_MAP = {
    1000 => 'M',
    900 => 'CM',
    500 => 'D',
    400 => 'CD',
    100 => 'C',
    90 => 'XC',
    50 => 'L',
    40 => 'XL',
    10 => 'X',
    9 => 'IX',
    5 => 'V',
    4 => 'IV',
    1 => 'I'
  }



  def to_roman

    input_number = self
    roman = ""
    NUMBER_TRANS_MAP.each_pair { |a,r|
      while input_number >= a
       input_number -= a
       roman << r
      end
    }
    roman
  end
end
