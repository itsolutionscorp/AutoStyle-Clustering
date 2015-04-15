class PhoneNumber
  attr_reader :number

  def initialize number
    validate = number.gsub(/[^\d]/, '')
    if valid_number? validate
      @number = "0" * 10
    else
      @number = validate[-10..-1]
    end
  end

  def area_code
    @number[0..2]
  end
  
  def to_s
    area_code = @number[0..2]
    prefix = @number[3..5]
    postfix = @number[6..9]

    "(#{area_code}) #{prefix}-#{postfix}"
  end

  private
  def valid_number?(validate)
    valid_length?(validate) || valid_country_code?(validate)
  end

  def valid_length?(temp)
    temp.length < 10 || temp.length > 11
  end

  def valid_country_code?(temp)
    temp[-11] != nil && temp[0] != "1"
  end
end
