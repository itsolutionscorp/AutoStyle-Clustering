class Binary
  def initialize(string)
    @string = String(string)
  end

  def to_decimal
    return 0 if @string[/[^01]/]

    @string.reverse.each_char.with_index
      .map { |c, i| 2**i if c == '1' }
      .compact.reduce(0, :+)
  end
end
