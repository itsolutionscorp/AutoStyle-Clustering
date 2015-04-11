class Hexadecimal
  HEX = [*'0'..'9',*'a'..'f']

  def initialize(input)
    @hex = input
  end

  def to_decimal
    return 0 if @hex.match(/[^#{HEX}]/)
    @hex.chars.reverse_each.with_index.inject(0) do |result, (chr,indx)|
      result + HEX.index(chr) * 16**indx
    end
  end
end
