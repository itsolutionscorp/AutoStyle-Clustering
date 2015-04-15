class PhoneNumber
  attr_reader :num
  def initialize(num)
    @num = fix(num)
  end

  def fix(number)
    return '0' * 10 if number.match(/[a-zA-Z]/)
    number = number.gsub(/[^0-9]/, '')
  end

  def valid?
    if num.length == 10 || (num.length == 11 && num.start_with?('1'))
        return true
    else
        return false
    end
  end

  def number
    if valid?
     return num[/(\d{10})\z/, 1]
    else
      return '0000000000'
    end
  end

  def area_code
    return num[0..2]
  end

  def to_s
    if num.length == 11 && num.start_with?('1')
      return "(#{num[1..3]}) #{num[4..6]}-#{num[7..10]}"
    else
      return "(#{num[0..2]}) #{num[3..5]}-#{num[6..9]}"
    end
  end
end
