class PhoneNumber
  def initialize(raw_number)
    @raw_number = raw_number
  end

  def number
    valid_number? ? cleaned_up_number : "0000000000"
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{number[0..2]}) #{number[3..5]}-#{number[6..9]}"
  end

  private

  def cleaned_up_number
    number = select_digits(@raw_number)
    trim_first_digit(number)
  end

  def valid_number?
    does_not_contain_letters? && correct_length?
  end

  def does_not_contain_letters?
    @raw_number.scan(/[A-Za-z]/).empty?
  end

  def correct_length?
    cleaned_up_number.size == 10
  end

  def select_digits(number)
    number.scan(/\d+/).join
  end

  def trim_first_digit(number)
    trimmable_first_digit?(number) ? number[1..-1] : number
  end

  def trimmable_first_digit?(number)
    number.size == 11 && number.start_with?("1")
  end
end
