class PhoneNumber
  def initialize(phone_number_string)
    self.raw_phone_number = phone_number_string
  end

  def number
    @number ||= begin
                  number = raw_phone_number.gsub(/\D+/, '')
                  number = number[1..-1] if (number.size == 11 && number.start_with?('1'))
                  number = '0' * 10      if number.size != 10
                  number
                end
  end

  def area_code()   number[0...3]  end
  def prefix()      number[3...6]  end
  def line_number() number[6...10] end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  private

  attr_accessor :raw_phone_number
end
