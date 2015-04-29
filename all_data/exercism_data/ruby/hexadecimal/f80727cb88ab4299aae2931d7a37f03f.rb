class Hexadecimal
  HEXCHARS = ('0'..'9').to_a + ('a'..'f').to_a

  def initialize(hexstring)
    @hexstring = hexstring
  end

  def to_decimal
    return 0 unless valid?
    hexstring.each_char.reduce(0) do |total, char|
      total * 16 + value(char)
    end
  end

  private

  attr_reader :hexstring

  def value(char)
    return 0 if char.nil?
    HEXCHARS.index(char)
  end

  def valid?
    @valid ||= hexstring.each_char.all? { |char| HEXCHARS.include?(char) }
  end
end
