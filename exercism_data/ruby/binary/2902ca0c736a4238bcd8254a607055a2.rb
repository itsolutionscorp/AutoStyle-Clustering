class Binary
  def initialize(str)
    if str =~ /[^01]/
      @str = '0'
    else
      @str = str
    end
  end

  attr_reader :str
  
  def to_decimal
    str.chars.reverse.inject([0, 1]) do |(sum, factor), digit|
      sum += factor if digit == '1'
      [sum, factor * 2]
    end.first
  end
end
