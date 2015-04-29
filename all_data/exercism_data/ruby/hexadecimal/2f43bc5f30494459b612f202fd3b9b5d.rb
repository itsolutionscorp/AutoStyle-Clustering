class Hexadecimal

  HEX = ["0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"]

  def initialize(hexa)
    @hexa = hexa
  end

  def to_decimal
    return 0 unless @hexa.chars.all?{|c| HEX.include?(c.upcase)}

    @hexa.chars.each_with_index.inject(0) do |decimal, (char, i)|
      decimal += base_number(char) * position_value(i)
    end
  end

  private

  def base_number(char)
    HEX.index(char.upcase)
  end

  def position_value(i)
    16 ** (@hexa.size - i - 1)
  end

end
