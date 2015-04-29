class Binary

  def initialize(binary_string)
    @binary_string_array = binary_string.split("")
  end

  def to_decimal
    decimal_number = 0
    @binary_string_array = @binary_string_array.reverse
    @binary_string_array.each_with_index  do |number,index|
      decimal_number = decimal_number + (2 ** index) if number == "1"
      return 0 if ( number != '0' && number != '1' )
    end

    decimal_number
  end

end
