class Binary
  def initialize(num)
    @num = num
  end

  def to_decimal
    return 0 if @num =~ /[^01]/
    decimal = 0
    @num.chars.map(&:to_i).each do |n|
      decimal = case n
        when 1 then decimal * 2 + 1
        else decimal * 2
      end
    end
    decimal
  end

end
