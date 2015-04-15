class PhoneNumber
  attr_reader :ph_no, :bad_number

  def initialize(ph_string)
    @ph_no = ph_string
    @bad_number = '0000000000'
  end

  def number
    sanitized_number ||= sanitize_number
    case sanitized_number.length
    when 10 then sanitized_number
    when 11
      if sanitized_number[0] == "1"
        sanitized_number[1..-1]
      else
        bad_number
      end
    else
      bad_number
    end
  end

  def sanitize_number
    ph_no.match(/[a-zA-Z]/) || ph_no.gsub(/[\(\)\- \.]/, "")
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..9]}"
  end

  def area_code
    number[0..2]
  end
end
