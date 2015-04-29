class Phone

  attr_reader :number

  def initialize(number)
    @number = parse_number(number.dup.gsub(/\D/, ""))
  end

  def area_code
    number[0..2]
  end

  def prefix
    number[3..5]
  end

  def line_number
    number[6..9]
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  private

    TEN_ZEROS = "0" * 10
    NON_DIGITS = %r{\D/}

    def parse_number(number)
      number.gsub! NON_DIGITS, ""
      number    = TEN_ZEROS if should_be_all_zeros? number
      number[0] = ""        if should_remove_prefix? number
      number
    end

    def should_be_all_zeros?(number)
      too_short?(number) || (too_long?(number) && number[0] != "1")
    end

    def should_remove_prefix?(number)
      too_long?(number) && number[0] == "1"
    end

    def too_short?(number)
      number.length < 10
    end

    def too_long? number
      number.length > 10
    end

end
