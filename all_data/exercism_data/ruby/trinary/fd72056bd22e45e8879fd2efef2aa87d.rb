class Trinary
  def initialize(trinary_number)
    @trinary_number = trinary_number
  end

  def to_decimal
    return 0 if @trinary_number.match(/[\D]/)

    decimal_number = 0
    @trinary_number.split("").reverse.each_with_index { |num,index| decimal_number += num.to_i * ( 3 ** index ) }
    decimal_number
  end
end
