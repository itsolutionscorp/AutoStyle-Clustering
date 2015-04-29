class PhoneNumber

  INVALID_NUMBER = "0000000000".freeze

  def initialize(number)
    @number = clean_number(number)
    @number = @number.remove_char_at(0) if includes_leading_one?
  end

  def number
    return INVALID_NUMBER if invalid_length?
    @number
  end

  def area_code
    @number[0,3]
  end

  def to_s
    "(#{area_code}) #{middle_digits}-#{end_digits}"
  end

  private

  def invalid_length?
    @number.length != 10
  end

  def includes_leading_one?
    @number.length == 11 && @number.start_with?('1')
  end

  def middle_digits
    @number[-7,3]
  end

  def end_digits
    @number[-4,4]
  end

  def clean_number(number)
    number.gsub(/[^0-9]/, '')
  end
end

class String
  def remove_char_at(index)
    self[index] = ''
    self
  end
end
