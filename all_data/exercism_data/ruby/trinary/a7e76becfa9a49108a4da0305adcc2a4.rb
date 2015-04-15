class Trinary
  def initialize trinary
    @trinary = trinary
  end

  def to_decimal
    return 0 if @trinary[/[^0-2]/]
    sum = 0
    digits = @trinary.reverse.chars
    digits.each_with_index do |n, i|
      sum += n.to_i * (3 ** i)
    end
    sum
  end
end
