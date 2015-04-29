class Hexadecimal

  HEX_TO_DEC = Hash[(0..9).map { |n| [n.to_s, n] }].
    merge(Hash[("a".."f").map.with_index { |c, i| [c, 10 + i] }])

  attr_reader :string

  def initialize(string)
    @string = string.reverse
  end

  def to_decimal
    return 0 unless /^[0-9a-f]+$/ =~ string
    string.each_char.with_index.reduce(0) do |sum, (char, index)|
      sum + HEX_TO_DEC[char] * 16**index
    end
  end
end
