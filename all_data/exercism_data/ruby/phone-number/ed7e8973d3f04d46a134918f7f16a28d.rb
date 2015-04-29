class PhoneNumber
  attr_accessor :number

  INVALID_NUMBER_PATTERNS = [/[a-zA-Z]/, /[^1][0-9]{10}/, /[0-9]{12,}/, /^[0-9]{9}$/ ]
  def clean(number)
    cleaned_number =  number.gsub(/[()-. ]/,'')
    cleaned_number = "0"*10 if INVALID_NUMBER_PATTERNS.any?{|invalid_patten| invalid_patten.match(cleaned_number)}
    cleaned_number.size > 10 ? cleaned_number[1..11] : cleaned_number
  end

  def initialize(number)
    @number = clean(number)
  end

  def area_code
    @area_code ||= self.number[0..2]
  end

  def to_s
    @prett_number ||= "(#{self.number[0..2]}) #{self.number[3..5]}-#{self.number[6..9]}"
  end
end
