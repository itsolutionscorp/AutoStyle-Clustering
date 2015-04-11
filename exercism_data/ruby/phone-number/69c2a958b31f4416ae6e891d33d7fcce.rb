class PhoneNumber
  BAD_NUMBER = "0000000000"

  attr_reader :number

  def initialize(char_string)
    char_string = remove_chars(char_string, "[ ().-]")
    @number = remove_chars(char_string, "[^0-9]")
  end

  def area_code
    @number.slice(0..2)
  end

  def to_s
    "(#{self.area_code}) #{@number.slice(3..5)}-#{@number.slice(6..-1)}"
  end

  private

  def remove_chars(number, pattern)
    test_string = number.gsub(Regexp.new(pattern), "")
    case
      when test_string.length == 10
        test_string
      when test_string.length == 11 && test_string[0] == '1'
        test_string.slice(1..-1)
      else
        BAD_NUMBER
    end
  end

end
