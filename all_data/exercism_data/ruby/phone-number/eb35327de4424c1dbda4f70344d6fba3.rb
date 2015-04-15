class PhoneNumber
  def initialize(number)
    @number = clean(number) || "0000000000"
  end

  def number
    @number
  end

  def area_code
    parts[0]
  end

  def to_s
    "(%s) %s-%s" % parts
  end

private 

  def clean(number)
    return nil unless number =~ /^[\d\s.\-\(\)]+$/
    formatted = number.gsub(/\D/, '')
    return formatted[1..-1] if formatted.length == 11 && formatted[0] == '1'
    return nil if formatted.length != 10
    formatted
  end

  def parts
    number.scan(/(...)(...)(....)/).flatten
  end
end
