class PhoneNumber
  attr_reader :number

  def initialize number
    temp = number.gsub(/[^\d]/, '')
    if valid_length?(temp) || valid_country_code?(temp)
      @number = "0" * 10
    else
      @number = temp[-10..-1]
    end
  end

  def area_code
    @number[0..2]
  end
  
  def to_s
    "(#{@number[0..2]}) #{@number[3..5]}-#{@number[6..9]}"
  end

  private
  def valid_length?(validate)
    validate.length < 10 || validate.length > 11
  end

  def valid_country_code?(validate)
    validate[-11] != nil && validate[0] != "1"
  end
end
