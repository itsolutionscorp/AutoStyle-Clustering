class PhoneNumber

  def initialize(number)
    @number = number
  end

  def number
    result = @number.scan(/\d\w?/).join
    result.slice!(0) if result.size == 11 && result[0] == '1'
    result.size == 10 ? result : "0000000000"
  end

  def area_code
    @number.slice(0, 3)
  end

  def to_s
    result = number
    result = result.split('')
    result.insert(0, '(')
    result.insert(4, ')')
    result.insert(5, ' ')
    result.insert(9, '-')
    result.join
  end
end
