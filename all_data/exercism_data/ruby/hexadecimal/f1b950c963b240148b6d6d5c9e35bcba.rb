class Hexadecimal
  Hexdigits = '0123456789abcdef'.chars
  Value = Hash[*(Hexdigits.map{ |d| [d, Hexdigits.index(d)] }).flatten ]

  def initialize str
    str = str.downcase.chars
    str = ['0'] if str.detect { |s| !Hexdigits.include? s }
    @str = str
  end

  def to_decimal
    @str.inject(0) { |value, d| value = value*16 + Value[d] }
  end
end
