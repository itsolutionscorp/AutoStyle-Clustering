class PhoneNumber
  PHONE_REGEX = /\A 1? \(?(\d{3})\)? [\s.-]? (\d{3}) [-.]? (\d{4}) \Z/x
  NUMBER_FOR_FAILED_MATCH = "0" * 10

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
