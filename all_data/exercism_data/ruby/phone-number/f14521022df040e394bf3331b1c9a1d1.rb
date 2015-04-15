class PhoneNumber
  attr_reader :number
  attr_reader :area_code
  def initialize raw_number
    @number = clean(raw_number)
    @number = @number[1...@number.length] if hasCountryCode?
    @number = '0000000000' if isBadNumber? raw_number
    @area_code = @number[0..2]
  end

  def to_s
    "(#{@number[0..2]}) #{@number[3..5]}-#{@number[6..9]}"
  end

  private
  def clean raw_number
    raw_number.gsub(/[^0-9]/,'')
  end
  
  def hasCountryCode?
    @number[0] == '1' and @number.length == 11
  end

  def isBadNumber? raw_number
    @number.length != 10 or not raw_number.scan(/[a-zA-Z]/).empty?
  end
end
