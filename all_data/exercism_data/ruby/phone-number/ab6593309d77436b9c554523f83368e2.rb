class Phone < Struct.new(:dirty_number)
  def number
    valid? ? clean : '0000000000'
  end

  def digits
    @digits ||= dirty_number.gsub(/[^\d]/, '')
  end

  def valid?
    digits.length == 10 ||
      digits.length == 11 && digits.start_with?('1')
  end

  def clean
    digits.length == 11 ? digits[1..-1] : digits
  end

  def to_s
    "(#{area_code}) #{prefix}-#{suffix}"
  end

  def area_code
    number[0..2]
  end

  def prefix
    number[3..5]
  end

  def suffix
    number[6..9]
  end

end
