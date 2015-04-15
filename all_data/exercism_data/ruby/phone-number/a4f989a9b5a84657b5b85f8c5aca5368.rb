class Phone
  DEFAULT_NUMBER  = "0000000000"

  def initialize(input)
    @input = input
  end

  def number
    return DEFAULT_NUMBER unless valid?

    area_code + prefix + line_number
  end

  def area_code
    stripped.slice(-10, 3).join
  end

  def prefix
    stripped.slice(-7, 3).join
  end

  def line_number
    stripped.slice(-4, 4).join
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  private

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
