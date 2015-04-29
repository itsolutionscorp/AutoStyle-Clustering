class Hexadecimal
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
    return char.to_i if char =~ /[0-9]/
    char.ord - 87
  end

  def valid?
    hexstring.each_char.all? { |char| char =~ /[0-9a-f]/ }
  end
end
