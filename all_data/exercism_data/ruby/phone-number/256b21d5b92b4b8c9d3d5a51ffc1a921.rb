class Phone
  def initialize input
    @input = input
  end

  def number
    @number ||= clean_that_shit_up
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{number[0..2]}) #{number[3..5]}-#{number[6..-1]}"
  end

  private

  def clean_that_shit_up
    digits = input.scan(/\d+/).join("")
    digits = digits[1..-1] if digits.start_with?("1") && digits.size == 11
    if digits.size == 10
      digits
    else
      "0000000000"
    end
  end

  attr_accessor :input
end
