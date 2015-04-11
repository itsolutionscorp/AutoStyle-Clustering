class PhoneNumber

  def initialize(num)
    @number = num
  end

  def number
    clean_number
    @number
  end

  def clean_number
    @number = @number.scan(/\d+/).join
    @number = "0000000000" if invalid_number?
    trim
  end

  def invalid_number?
    @number.size > 10 && !@number.start_with?('1') || @number.size < 10
  end

  def trim
    @number[0] = '' if @number.size > 10 && @number.start_with?('1')
  end

  def area_code
    @number[0,3]
  end

  def first_half
    @number[3,3]
  end

  def last_half
    number[6,4]
  end

  def to_s
    clean_number
    "(#{area_code}) #{first_half}-#{last_half}"
  end
end
