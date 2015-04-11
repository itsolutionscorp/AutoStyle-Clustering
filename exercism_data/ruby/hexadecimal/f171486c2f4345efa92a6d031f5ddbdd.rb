class Hexadecimal

  HEX_VALS = {}
  (0..9).each { |i| HEX_VALS[i.to_s] = i }
  ('a'..'f').each_with_index {|char,index| HEX_VALS[char.to_s] = index + 10 }

  def initialize(hex)
    @hex = hex
  end

  def to_decimal
    return 0 if invalid_hex?
    total = 0
    each_place {|char, place| total += HEX_VALS[char] * 16 ** place}
    total
  end

  def each_place
    placed = @hex.chars.reverse

    placed.each_with_index do |char, place|
      yield char, place
    end
  end

  def invalid_hex?
    @hex =~ /[^0-9a-f]/
  end
end
