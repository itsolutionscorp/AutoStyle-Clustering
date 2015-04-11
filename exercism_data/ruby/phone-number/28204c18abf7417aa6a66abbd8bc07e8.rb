class Phone
  def initialize input
    @input = input
  end

  def number
    @number ||= if valid_length?
      cleaned_input
    else
      "0000000000"
    end
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{number[0..2]}) #{number[3..5]}-#{number[6..-1]}"
  end

  private

  def cleaned_input
    if leading_1_and_11_digits_long?
      digits[1..-1]
    else
      digits
    end
  end

  def digits
    @digits ||= input.scan(/\d+/).join("")
  end

  def leading_1_and_11_digits_long?
    digits[0] == "1" && digits.size == 11
  end

  def valid_length?
    cleaned_input.size == 10
  end

  attr_accessor :input
end
