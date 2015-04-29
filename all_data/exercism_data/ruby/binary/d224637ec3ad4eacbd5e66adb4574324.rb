class Binary
  
  def initialize(digits)
    @digits = is_binary?(digits) ? digits.split('').reverse : ['0']
  end

  def to_decimal
   decimal = @digits[0].to_i
   for i in (1..@digits.size)
     decimal += (2 * @digits[i].to_i) ** i
   end
   decimal
  end

  private

  def is_binary?(word)
    !!(word =~ /^[0-1]+$/)
  end

end
