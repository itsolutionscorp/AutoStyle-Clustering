class Trinary
  def initialize(str)
    if str =~ /[^012]/
      @str = '0'
    else
      @str = str
    end
  end

  attr_reader :str
  
  def to_decimal
    str.chars.reverse.inject([0, 1]) do |(sum, factor), digit|
      sum += factor if digit == '1'
      sum += 2 * factor if digit == '2'
      [sum, factor * 3]
    end.first
  end
end
