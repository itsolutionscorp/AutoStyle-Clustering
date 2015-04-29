class Hexadecimal
  Hexdigits = ((0..9).to_a + ('a'..'f').to_a).map { |d| d.to_s }
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
