class Binary
  def initialize(binary_number)
    @number = binary_number
  end

  def to_decimal
    decimal = 0
    @number.split('').reverse.each_with_index do |digit, index|
      case digit
      when '0'
      when '1'
        decimal += 2**index
      else fail
      end
    end 
    decimal
  rescue
    0
  end
end
