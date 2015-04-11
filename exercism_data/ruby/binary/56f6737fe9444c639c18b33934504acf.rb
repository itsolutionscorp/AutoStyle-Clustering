class Binary
  attr_accessor :value

  def initialize(value)
    @value = value
  end

  def to_decimal
    if valid_binary?(self.value)
      binary = self.value
    else
      return 0
    end

    result = 0
    digit_place = binary.length - 1
    binary.each_char do |digit|
      result += digit.to_i * (2 ** digit_place)
      digit_place -= 1
    end
    result
  end

  def valid_binary?(string)
    string.match("^[01]*$") ? true : false
  end
end
