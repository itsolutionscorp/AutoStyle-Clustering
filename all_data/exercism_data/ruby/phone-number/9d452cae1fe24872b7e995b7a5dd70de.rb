class PhoneNumber
  attr_reader :number

  def initialize(value)
    @number = value.gsub(/[^\d]/,'')
    strip_trunk_code
    ensure_valid_length
  end

  def to_s
    "(%s) %s-%s" % number.scan(/(...)(...)(....)/).flatten
  end

  def area_code
    number[0...3]
  end

  private

  def strip_trunk_code
    @number = number[1..-1] if has_trunk_code? 
  end

  def ensure_valid_length
    @number = "0"*10 unless (number.length == 10 || has_trunk_code?)
  end

  def has_trunk_code?
    number.start_with?('1') && number.length == 11
  end

end
