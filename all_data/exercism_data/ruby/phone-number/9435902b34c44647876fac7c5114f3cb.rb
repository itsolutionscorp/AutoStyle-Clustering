class PhoneNumber
  attr_reader :number

  def initialize(value)
    clean_number(value)
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{area_code}) #{@number[3..5]}-#{@number[6..9]}"
  end

  private
  def clean_number(value)
    @number = value.to_s
    remove_non_digits
    pop_one_off_eleven_digits
    validate_length
  end

  private
  def remove_non_digits
    @number.gsub!(/\D/, '')
  end

  private
  def pop_one_off_eleven_digits
    @number.sub!(/^./,'') if @number.size == 11 && number[0] == '1'
  end

  private
  def validate_length
    @number = '0000000000' unless @number.size == 10
  end
end
