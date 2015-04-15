class PhoneNumber
  def initialize input
    @input = input
  end

  FILTERS = [:remove_special_characters, :check_digit_count, :handle_leading_digit]
  INVALID = "0000000000"

  def number
    @number ||= FILTERS.inject(@input) { |number, method| send method, number }
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..9]}"
  end

  private

  def check_digit_count input
    case input.length
    when (10..11)
      input
    else
      INVALID
    end
    input.length === (10..11) ? input : INVALID
  end

  def remove_special_characters number
    number.gsub(/[() \-.]/, "")
  end

  def handle_leading_digit number
    if number.length == 11
      number[0] == "1" ? number[1..10] : INVALID
    else
      number
    end
  end
end
