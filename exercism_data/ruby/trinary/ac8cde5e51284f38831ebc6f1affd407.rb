Trinary = Struct.new(:num) do
  def to_decimal
    digits = num.reverse.chars.map { |s| s.to_i }
    digits.each_with_index.inject(0) do |sum, (n,i)|
      sum + n * 3**i
    end
  end
end
