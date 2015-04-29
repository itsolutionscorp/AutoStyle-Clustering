class Binary
  def initialize(s)
    @number = s
  end
  
  def to_decimal
    result = 0
    val = 1
    @number.reverse.each_char do |c|
      return 0 if c.delete('01') == c
      result += val if c == '1'
      val *= 2
    end
    return result
  end
end
