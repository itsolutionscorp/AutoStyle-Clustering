class Trinary
  attr_reader :trinary_number

  def initialize(trinary_number)
    @trinary_number = trinary_number
  end

  def to_decimal
    ## Ruby solves this, but defeats the purpose of the exercise
    # trinary_number.to_i(3)
    return 0 if trinary_number =~ /[^0-2]/
    decimal = 0
    trinary_number.reverse.each_char.with_index do |char, index|
      decimal += char.to_i * 3**index
    end
    decimal
  end
end
