class Phone

  def initialize(phone_number)
    @number = phone_number.gsub(/\D/, '')
  end

  def number
    validated_number
  end

  def area_code
    number[0..2]
  end

  def to_s
    local_prefix = number[3..5]
    extension = number[6..9]
    pretty_number ||= "(#{area_code}) #{local_prefix}-#{extension}"
  end

  private

  def validated_number
    if long_distance
      @number[1..-1]
    elsif too_many_digits || not_enough_digits
      @number = "0000000000"
    else
      @number
    end
  end

  def long_distance
    @number.length == 11 && @number[0] == '1'
  end

  def too_many_digits
    @number.length > 10
  end

  def not_enough_digits
    @number.length < 10
  end

end
