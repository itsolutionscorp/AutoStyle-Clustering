class Binary

  def initialize(binary_string)
    @binary = binary_string.reverse
  end

  def to_decimal
    return 0 if @binary =~ /[a-zA-Z]/
    range.inject(0) do |r, n|
      r += ( @binary[n].to_i * (2 ** n))
    end
  end

  def range
    (0...@binary.length).to_a
  end

end
