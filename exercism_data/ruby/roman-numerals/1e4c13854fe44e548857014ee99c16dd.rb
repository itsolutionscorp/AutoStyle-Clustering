class Fixnum
  CONVERSIONS = {
    1000=>'M',
    900=>'CM',
    500=>'D',
    400=>'CD',
    100=>'C',
    90=>'XC',
    50=>'L',
    40=>'XL',
    10=>'X',
    9=>'IX',
    5=>'V',
    4=>'IV',
    1=>'I'
  }

  def to_roman
    return '' if zero?
    arabic, roman = conversions_factor_for self
    roman + (self - arabic).to_roman
  end

  private
  def conversions_factor_for in_arabic
    CONVERSIONS.find { |arabic, _| arabic <= in_arabic }
  end
end
