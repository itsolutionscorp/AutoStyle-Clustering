class Binary
  def initialize(string)
    @string = string
  end

  def to_decimal
    return 0 unless @string =~ /\A[10]+\Z/
    @string.chars.reduce(0) do |sum, char|
      sum * 2 + (char == "1" ? 1 : 0)
    end
  end
end
