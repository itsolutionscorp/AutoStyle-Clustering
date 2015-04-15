class PhoneNumber
  PHONE_REGEX = /\A 1? \(?(\d{3})\)? [\s.-]? (\d{3}) [-.]? (\d{4}) \Z/x
  NUMBER_FOR_FAILED_MATCH = "0" * 10

  AREA_CODE   = (0..2)
  FIRST_THREE = (3..5)
  LAST_FOUR   = (6..10)

  def initialize(raw_number)
    @raw_number = raw_number
  end

  def number
    @number ||= format_number(@raw_number)
  end

  def area_code
    @area_code ||= number[AREA_CODE]
  end

  def to_s
    "(#{number[AREA_CODE]}) #{number[FIRST_THREE]}-#{number[LAST_FOUR]}"
  end

  private

  def format_number(raw_number)
    match = raw_number.match(PHONE_REGEX)
    match ? match.captures.join : NUMBER_FOR_FAILED_MATCH
  end
end
