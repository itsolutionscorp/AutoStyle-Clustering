class PhoneNumber
  def initialize(number)
    @phone_number = number
  end

  def number
    remove_non_number
  end

  def area_code
    p_num = self.number
    p_num.slice(0..2)
  end

  def to_s
    p_num = self.number
    "(#{p_num.slice(0..2)}) #{p_num.slice(3..5)}-#{p_num.slice(6..9)}"
  end

  private

  def remove_non_number
    return '0000000000' if has_letters?

    @phone_number = @phone_number.scan(/[0-9]/)
    return '0000000000' unless is_over_10digits? && is_over_11digits?
    return trim_first_number_of_11digits if is_11digits?
    @phone_number.join
  end

  def has_letters?
    !@phone_number.scan(/[a-zA-Z]+/).empty?
  end

  def is_over_10digits?
    return false if @phone_number.length < 10
    true
  end

  def is_over_11digits?
    return false if @phone_number.length > 11
    true
  end

  def is_11digits?
    return true if @phone_number.length == 11
    false
  end

  def trim_first_number_of_11digits
    return @phone_number.slice(1,10).join if @phone_number[0] == '1'
    '0000000000'
  end


end
