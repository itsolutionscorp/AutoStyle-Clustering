class PhoneNumber
  attr_reader :number

  def initialize(phone_number)
    @number = phone_number.chars.select{ |x| x =~ /\w/ }.join
    @number = @number[/^1?(\d{10})$/, 1]
    @number ||= '0000000000'
  end

  def area_code
    @number[0, 3]
  end

  def local_number
    "#{@number[3, 3]}-#{@number[6,4]}"
  end

  def to_s
    "(#{area_code}) #{local_number}"
  end
end
