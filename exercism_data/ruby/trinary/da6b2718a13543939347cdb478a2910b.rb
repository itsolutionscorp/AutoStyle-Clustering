class Trinary

  attr_reader :number

  def initialize(n)
    @number = n
  end

  def to_decimal
    return 0 if !number[/[0-9]/]
    number.chars.reduce(0) do |decimal, digit|
      (decimal * 3) + Integer(digit)
    end
  end

end
