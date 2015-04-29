class Phone

  NUMBER_LENGTH = 10
  AREA_CODE_RANGE = 0..2
  PREFIX_RANGE = 3..5
  LINE_NUMBER_RANGE = 6..10

  def initialize(number)
    @unsanitized = number
  end

  def number
    @number ||= validated
  end

  def area_code
    number[AREA_CODE_RANGE]
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  private

  attr_reader :unsanitized

  def prefix
    number[PREFIX_RANGE]
  end

  def line_number
    number[LINE_NUMBER_RANGE]
  end

  def validated
    trimmed.length == NUMBER_LENGTH ? trimmed : "0000000000"
  end

  def trimmed
    includes_country_code? ? sanitized.slice(1, NUMBER_LENGTH) : sanitized
  end

  def sanitized
    unsanitized.gsub(/\D/,"")
  end

  def includes_country_code?
    sanitized.start_with?("1") && sanitized.length == NUMBER_LENGTH + 1
  end

end
