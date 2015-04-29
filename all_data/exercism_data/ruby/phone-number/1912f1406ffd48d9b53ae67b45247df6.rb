class PhoneNumber
  attr_reader :number

  def initialize(number_as_string)
    @number = remove_allowed_chars(number_as_string)
    @number = "0" * 10 if @number.match /[^\d]/
    @number = @number.each_char.drop(1).join if @number.size == 11 && @number[0] == "1"
    @number = "0" * 10 if @number.size != 10
  end

  def area_code
    @number[0,3]
  end

  def to_s
    "(#{area_code}) #{@number[3,3]}-#{@number[6,4]}"
  end

  def remove_allowed_chars(str)
    str.gsub /[()\-\.\s]/, ''
  end

end
