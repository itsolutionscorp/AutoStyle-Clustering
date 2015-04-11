class Phone
  attr_reader :number

  def initialize(input)
    @number = validate(trim(input))
  end

  def area_code
    format_number[0]
  end

  def office_code
    format_number[1]
  end

  def station_code
    format_number[2]
  end

  def to_s
    "(#{area_code}) #{office_code}-#{station_code}"
  end

  private

  def trim(input)
    input.gsub(/\D/, '')
  end

  def validate(input)
    if input =~ /\A1?(\d{10})\z/
      $1
    else
      "0" * 10
    end
  end

  def format_number
    /(\d{3})(\d{3})(\d{4})/.match(number).captures
  end
end
