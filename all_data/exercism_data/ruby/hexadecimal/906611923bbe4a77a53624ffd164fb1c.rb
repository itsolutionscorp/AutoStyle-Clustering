class Hexadecimal
  attr_reader :string

  def initialize(str)
    @string = str
  end

  def to_decimal
    string.chars.reverse.zip(places).inject(0) do |result, (char, multiplier)|
      result + CHARACTERS.fetch(char){ return 0 } * multiplier
    end
  end

  private

  CHARACTERS = Hash[(('0'..'9').to_a + ('a'..'f').to_a).zip(0..15)]

  def places
    (0..Float::INFINITY).lazy.map {|x| 16 ** x }
  end

end
