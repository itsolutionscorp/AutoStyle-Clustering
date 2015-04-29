class PhoneNumber
  def initialize(number)
    @number = clean_number(number)
  end
  
  def number
    return '0000000000' unless valid?(@number)
    @number
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{area_code}) #{@number[3..5]}-#{@number[6..9]}"
  end

  private

  def clean_number(number)
    num = number.gsub(/[\(\)\-\.\s]/, '')

    if num.length == 11 && num[0] == '1'
      num[1..-1]
    else
      num
    end
  end

  def valid?(number)
    number.length == 10
  end
end
