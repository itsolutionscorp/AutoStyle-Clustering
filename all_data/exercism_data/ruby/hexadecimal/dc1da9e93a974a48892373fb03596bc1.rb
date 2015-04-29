class Hexadecimal
  attr_reader :sequence

  def initialize(string)
    @sequence = string.match(/^[0-9a-f]+$/) ? string.chars : []
  end

  def to_decimal
    sequence.reverse.each_with_index.inject(0) do |result, (elem, index)|
      result + hex_map[elem] * (16 ** index)
    end
  end

  def hex_map
    Hash.new.tap do |hash|
      (0..9).each { |n| hash[n.to_s] = n }

      ('a'..'f').each_with_index do |c, index|
        hash[c] = 10 + index
      end
    end
  end
end
