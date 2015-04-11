class PhoneNumber
  attr_accessor :number

  VALID_NUMBER_PATTERN = /^(1)?[0-9]{10}$/
  def clean(number)
    cleaned_number =  number.gsub(/[()-. ]/,'')
    cleaned_number = "0"*10 unless VALID_NUMBER_PATTERN.match(cleaned_number)
    cleaned_number.size > 10 ? cleaned_number[1..11] : cleaned_number
  end

  def initialize(number)
    @number = clean(number)
  end

  def area_code
    @area_code ||= self.number[0..2]
  end

  def local_prefix
    @local_prefix ||= self.number[3..5]
  end

  def line_number
    @line_number ||= self.number[6..9]
  end

  def to_s
    @pretty_number ||= "(#{self.area_code}) #{self.local_prefix}-#{self.line_number}"
  end
end
