class Trinary
  def initialize number
    @number = number
    @number_array = @number.chars.reverse.map(&:to_i)
  end

  def to_decimal
    return 0 unless is_valid?
    @number_array.each_with_index.inject(0) do |memo,(value,index)|
      memo + value*3**index
    end
  end

  def is_valid?
    @number =~ /\d/
  end
end
