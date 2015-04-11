class Binary
  def initialize(string)
    @string = string
  end

  def to_decimal
   if contains_illegal_chars?
      return 0
    end
    calculate_result
  end

  private

  def calculate_result
    result = 0
    exponent = 0
    -1.downto(-32) do |index|
      result += @string[index].to_i * 2**exponent
      exponent += 1
    end
    result
  end

  def contains_illegal_chars?
    @string.match(/^(?:1*(?:01*0)*)+|0*1(?:0*(?:10*1)*)*$/).to_s.length == 0
  end
end
