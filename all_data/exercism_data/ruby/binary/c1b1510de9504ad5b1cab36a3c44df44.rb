class Binary
  attr_accessor :binary_number
  
  def initialize(input='0')
    if /[^0-1]/.match(input)
      @binary_number = '0'
    else
      @binary_number = input
    end
  end

  def to_decimal
    reverse = (0..binary_number.length-1).to_a.reverse
    num = 0

    (0..binary_number.length-1).each do |i|
      num += binary_number[i].to_f * 2**reverse[i]
    end

    num
  end
end
