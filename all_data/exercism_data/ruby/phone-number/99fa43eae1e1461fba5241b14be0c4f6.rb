class PhoneNumber
  attr_reader :number
  attr_reader :area_code
  
  # Matches valid phone numbers. Group 1 captures the 10-digit number.
  PHONENUMBER_RE = /^1?(\d{10})$/

  def initialize string
    # Strip expected non-digits, and try to match what remains.
    string.tr(' ().-', '') =~ PHONENUMBER_RE
    @number = $1 || '0000000000'
    @area_code = @number[0, 3]
  end

  def to_s
    "(#{@area_code}) #{@number[3, 3]}-#{@number[6, 4]}"
  end
end
