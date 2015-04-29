class Binary
  def initialize(string)
    @string = String(string)
  end

  def to_decimal
    return 0 if @string[/[^01]/]

    @string.reverse.each_char.with_index.reduce(0) do |d, (c, i)|
      c == '0' ? d : d + 2**i
    end
  end
end
