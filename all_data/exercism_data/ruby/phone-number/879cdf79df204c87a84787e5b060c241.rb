class PhoneNumber
  PHONE_REGEX = /
    \A                # start of string
    1?                # optional leading 1
    \(?(\d{3})\)?     # first 3 digits with optional parenthesis
    [\s.-]?           # optional separator
    (\d{3})           # middle 3 digits
    [-.]?             # optional separator
    (\d{4})           # last 4 digits
    \Z                # end of string
  /x

  NUMBER_FOR_FAILED_MATCH = "0000000000"

  def initialize(raw_number)
    @raw_number = raw_number
  end

  def number
    @number ||= format_number(@raw_number)
  end

  def area_code
    @area_code ||= number[0..2]
  end

  def to_s
    "(%s) %s-%s" % [area_code, number[3..5], number[6..10]]
  end

  private

  def format_number(raw_number)
    if (match_data = raw_number.match(PHONE_REGEX))
      match_data.captures.join
    else
      NUMBER_FOR_FAILED_MATCH
    end
  end
end
