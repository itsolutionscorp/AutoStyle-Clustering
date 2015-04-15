class PhoneNumber
  def initialize(number)
    @number = number.delete('- .()')
  end

  def number
    @number =~ /^\d+$/ && @number.length == 10 ? @number : validate_11
  end

  def area_code
    number[0..2]
  end

  def to_s
    number.insert(0, '(').insert(4, ') ').insert(9, '-')
  end

  private
  def validate_11
    @number.length == 11 && @number.start_with?('1') ? @number[1..-1] : '0' * 10
  end
end
