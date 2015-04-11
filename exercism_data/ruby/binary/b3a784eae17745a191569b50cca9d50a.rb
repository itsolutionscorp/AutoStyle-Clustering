class Binary
  def initialize(number)
    @number = number unless number.chars.any?{|c| c =~ /\D/}
  end

  def to_decimal 
    @number.to_s.chars.each_with_index.inject(0) do |sum, (value, index)|
      sum += value.to_i * 2 ** (@number.length - index - 1)
    end
  end

end
