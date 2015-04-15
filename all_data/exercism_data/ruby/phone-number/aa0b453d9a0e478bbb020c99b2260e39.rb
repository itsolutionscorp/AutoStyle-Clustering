class Phone
  attr_reader :area_code, :prefix, :line_number

  DEFAULT_NUMBER  = "0000000000"

  def initialize(input)
    @input = input
    @area_code, @prefix, @line_number = number_to_parts
  end

  def number
    return DEFAULT_NUMBER unless valid?

    area_code + prefix + line_number
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  private

  def number_to_parts
    area_code   = stripped.slice(-10, 3)
    prefix      = stripped.slice(-7, 3)
    line_number = stripped.slice(-4, 4)
    [area_code, prefix, line_number].map(&:join)
  end

  def valid?
    stripped.length == 10 || us_number?
  end

  def us_number?
    stripped.length == 11 && stripped.first == "1"
  end

  def stripped
    @input.scan(/\d/)
  end
end
