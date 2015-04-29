Binary = Struct.new(:number) do
  def to_decimal
    return 0 if number =~ /[^10]/
    nums = number.reverse.chars.map { |s| s.to_i }
    nums.each_with_index.inject(0) do |sum, (n,i)|
      sum + n * (2**i)
    end
  end
end
