class PhoneNumber
  attr_reader :number

  def initialize(number)
    @number = clean number
  end

  def area_code
    number[0..2]
  end

  def local_number
    number[3..-1].insert(3,'-')
  end

  def to_s
    "(#{area_code}) #{local_number}"
  end

private

  def clean(input)
    number = input.delete " -.\(\)a-z"
    clean_long_distance number
  end

  def clean_long_distance(num)
    return num if num.size == 10
    return num[1..-1] if num.size == 11 && num.start_with?('1')
    '0'*10
  end

end
