class PhoneNumber
  def initialize(number_str)
    @number = numbers_only number_str
  end

  def number
    valid? ? @number : default_number
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{number[0..2]}) #{number[3..5]}-#{number[6..10]}"
  end

  private

  def numbers_only(number_str)
    number_str.scan(/\w+/).join
  end

  def default_number
    '0' * 10
  end

  def valid?(nb = @number)
    l = nb.length
    !(nb =~ /[a-zA-Z]/) && l == 10 || (l == 11 && nb.slice!(0) == '1')
  end
end
