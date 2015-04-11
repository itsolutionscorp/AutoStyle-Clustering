class PhoneNumber
  def initialize(string)
    @number = valid?(string) ? convert_to_number(string) : '0000000000'
  end

  def area_code
    @number[0..2]
  end

  def to_s
    string = number
    string.insert(0, '(')
    string.insert(4, ') ')
    string.insert(9, '-')
  end

  def number
    @number[0] = '' if @number.length > 10
    @number
  end

  private

  def convert_to_number(string)
    string.gsub(/[^0-9]/, "")
  end

  def valid?(string)
    if string.match(/[a-zA-Z]/)
      false
    elsif string.length < 10
      false
    elsif convert_to_number(string).length == 11 && convert_to_number(string)[0] != '1'
      false
    elsif convert_to_number(string).length == 11 && convert_to_number(string)[0] == '1'
      true
    elsif convert_to_number(string).length > 10
      false
    else
      true
    end
  end
end
