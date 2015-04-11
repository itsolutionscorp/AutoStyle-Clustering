require 'pry'

class PhoneNumber
  ERROR_OUTPUT = '0000000000'
  def initialize(string)
    @string = string
  end

  def number
    return ERROR_OUTPUT if @string =~ /[a-zA-Z]/

    output = numbers_only(@string)

    if output.size == 10
      output
    elsif output[0] == '1' && output.size == 11
      output[1..-1]
    else
      ERROR_OUTPUT
    end
  end

  def numbers_only(string)
    string.gsub(/[\D]/,'')
  end

  def area_code
    number[0..2]
  end

  def first_digits
    number[3..5]
  end

  def last_digits
    number[6..9]
  end

  def to_s
    "(#{area_code}) #{first_digits}-#{last_digits}"
  end
end
