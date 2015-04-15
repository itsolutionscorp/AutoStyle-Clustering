class PhoneNumber
  ERROR_CODE = '0000000000'

  def initialize(number)
    @number = number
  end

  def number
    @number.gsub!(/[^\d]/, '')
    @number = sanitize(@number)
  end

  def area_code
    number[0..2]
  end

  def to_s
    pretty = number
    addins = {0 => '(', 4 => ')', 5 => ' ', 9 => '-'}

    addins.each do |i, char|
      pretty.insert i, char
    end

    pretty
  end

  private

  def sanitize(number)
    return ERROR_CODE unless [10, 11].include? number.length

    if number.length == 11
      return ERROR_CODE unless number.start_with?('1')
      number[0] = ''
    end
    number
  end
end
