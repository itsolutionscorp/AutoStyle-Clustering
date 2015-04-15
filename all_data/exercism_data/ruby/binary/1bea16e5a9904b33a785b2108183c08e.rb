class Binary
  attr_accessor :binary_string
  
  def initialize(binary_string)
    @binary_string = binary_string
  end

  def to_decimal
    return 0 if binary_string =~ /[^01]/

    array = binary_string.split('').reverse
    result = 0

    array.each_with_index do |e, i|
      result += 2 ** i if e == '1'
    end

    result
  end
end
