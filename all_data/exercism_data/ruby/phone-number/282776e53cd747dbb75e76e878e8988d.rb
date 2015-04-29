class PhoneNumber
  attr_reader :number

  def initialize(number)
    validate = number.gsub(/[^\d]/, '')

    @number = valid_number?(validate) ? validate[-10..-1] : "0" * 10
  end
  
  def area_code
    @number[0..2]
  end

  def to_s
    "(#{area_code}) #{@number[3..5]}-#{@number[6..9]}"
  end

  private
  def valid_number?(validate)
    valid_length?(validate) && valid_country_code?(validate)
  end

  def valid_length?(validate)
    [10, 11].include? validate.length
  end

  def valid_country_code?(validate)
    validate[-11] == nil || validate[0] == "1"
  end
end
