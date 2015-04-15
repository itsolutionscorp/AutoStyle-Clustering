class Binary
  attr_reader :string
  def initialize(string)
    @string = string
  end

  def to_decimal
    return 0 if string.match(/[a-zA-Z]/)
    string.reverse.chars.map.with_index{ |char, index| char.to_i * 2 ** index }
    .inject(:+)
  end
end
