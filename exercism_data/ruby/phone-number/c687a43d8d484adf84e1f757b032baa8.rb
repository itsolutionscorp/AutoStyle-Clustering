class Phone
  attr_reader :digits

  BAD_NUMBER = '0000000000'

  def initialize(string)
    @digits = string.gsub(/\D/, '')
    validate!
  end

  def number
    digits
  end

  def area_code
    digits[0..2]
  end

  def to_s
    formatted
  end

  private

  def validate!
    remove_area_code_prefix
    enforce_length_10
  end

  def remove_area_code_prefix
    if digits.length == 11 && digits[0] == '1'
      digits[0] = ''
    end
  end

  def enforce_length_10
    @digits = BAD_NUMBER if digits.length != 10
  end

  def formatted
    "(#{digits[0..2]}) #{digits[3..5]}-#{digits[6..-1]}"
  end
end
