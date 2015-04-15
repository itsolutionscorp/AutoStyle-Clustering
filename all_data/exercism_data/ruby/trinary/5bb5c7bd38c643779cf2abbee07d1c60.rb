class Trinary
  def initialize(string)
    @string = string
  end

  def to_decimal
    return 0 if @string =~ /[^012]/

    result = 0
    digits = @string.chars.map{|char| char.to_i}.reverse

    digits.each_with_index do |digit, index|
      result += digit * (3 ** index)
    end

    result
  end
end
