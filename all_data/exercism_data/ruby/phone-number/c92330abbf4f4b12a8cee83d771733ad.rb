class PhoneNumber
  attr_reader :number

  def initialize(phone)
    digits = phone.gsub(/[[:punct:][:space:]]/, '').match(/(\d{10}\d*)/)
    if !digits.nil?
      case digits[1].length
      when 10
        @number = digits[1]
      when 11
        @number = digits[1][1..-1] if digits[1].start_with?('1')
      end
    end
    @number ||= '0000000000'
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{area_code}) #{@number[3..5]}-#{@number[6..9]}"
  end
end
