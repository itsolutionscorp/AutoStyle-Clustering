class Binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    return 0 if @binary.gsub(/[^01]/,"") != @binary

    power = @binary.length
    @binary.chars.reduce(0) do |sum, char|
      power -= 1
      char == "1" ? sum + (2**power) : sum
    end
  end

end
