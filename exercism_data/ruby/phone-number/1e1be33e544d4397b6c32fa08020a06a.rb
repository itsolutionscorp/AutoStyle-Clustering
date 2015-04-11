class PhoneNumber
  ERROR = "0000000000"
  def initialize(string)
    @string = string
    @digits_only = string.gsub(/[^0-9a-z]/i, '')
  end
  attr_reader :string

  def number
    return ERROR if has_letters(string) == false
    return ERROR if @digits_only.length < 10 || @digits_only.length > 11
    if @digits_only.length == 11 && @digits_only[0] == '1'
      @digits_only[0] = ''
      @digits_only
    elsif @digits_only.length == 11 && @digits_only[0] != '1'
      ERROR
    else
      @digits_only
    end
  end

  def area_code
    @digits_only[0..2]
  end

  def to_s
   number = self.number
   "(#{number[0..2]}) #{number[3..5]}-#{number[6..9]}"
  end

  def has_letters(string)
    string.gsub(/[^a-z]/i, '').empty?
  end

end
