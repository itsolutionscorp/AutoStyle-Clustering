class Trinary
  attr_accessor :string_number

  def initialize(string_number)
    @string_number = string_number
  end

  def to_decimal
    string_number.to_i(3)
  end

end
