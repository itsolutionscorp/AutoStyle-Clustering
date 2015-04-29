class Phone

  def initialize(number)
    @raw_number = number
  end

  def to_s
    "(#{area_code}) #{prefix}-#{suffix}"
  end

  def number
    @clean_number ||= clean_and_parse
  end

  def area_code
    number[0..2]
  end

  def prefix
    number[3..5]
  end

  def suffix
    number[6..9]
  end

  def clean_and_parse
    clean = @raw_number.gsub(/[^0-9]/,"")
    if clean.length == 10
      clean
    elsif clean.length == 11 && clean[0] == "1"
      clean[1..-1]
    else
      "0000000000"
    end
  end

end
