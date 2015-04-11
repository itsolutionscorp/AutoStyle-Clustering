class PhoneNumber
  def initialize(number)
    @number = clean(number.clone) || "0000000000"
  end

  def number
    @number
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(%s) %s-%s" % [area_code, number[3..5], number[6..-1]]
  end

private 

  def clean(number)
    return nil unless number =~ /^[\d\s.\-\(\)]+$/
    number.gsub!(/\D/, '')
    return number[1..-1] if number.length == 11 && number[0] == '1'
    return nil if number.length != 10
    number
  end
end
