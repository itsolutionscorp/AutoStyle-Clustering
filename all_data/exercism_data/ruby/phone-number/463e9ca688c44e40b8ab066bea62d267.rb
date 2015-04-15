class PhoneNumber

  BAD = "0" * 10

  def initialize phone
    @phone = phone.gsub(/\D/, '')
  end

  def number
    @number ||= case @phone.size
    when 0..9
      BAD
    when 10
      @phone
    when 11..Float::INFINITY
      @phone.start_with?('1') ? @phone[1..-1] : BAD
    end
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..-1]}"
  end
end
