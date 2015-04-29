class Binary
  def initialize(binary)
    if binary =~ /^[01]+$/
      @value = binary.split(//).reverse.each.with_index.inject(0) do |sum, (char, idx)|
        sum + (char == '1' ? 2 ** idx : 0)
      end
    else
      @value = 0
    end
  end

  def to_decimal
    @value
  end
end
