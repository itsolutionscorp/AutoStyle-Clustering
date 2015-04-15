class PhoneNumber
  attr_reader :area_code, :central_office_code, :station_code

  def initialize(value)
    parse(value)
  end

  def number
    [area_code, central_office_code, station_code].join
  end

  def to_s
    "(#{area_code}) #{central_office_code}-#{station_code}"
  end

  private

  def parse(value)
    @numbers = extract_digits(value)

    remove_country_code if has_country_code?
    bad_number unless has_correct_size?

    extract_area_code
    extract_central_office_code
    extract_station_code
  end

  def extract_digits(value)
    value.gsub(/\D/, "").chars
  end

  CORRECT_SIZE = 10

  def has_correct_size?
    @numbers.size == CORRECT_SIZE
  end

  def has_country_code?
    @numbers.size == CORRECT_SIZE + 1 and @numbers.first == "1"
  end

  def remove_country_code
    @numbers.shift
  end

  def bad_number
    @numbers = ["0"] * 10
  end

  def extract_area_code
    @area_code = @numbers.shift(3).join
  end

  def extract_central_office_code
    @central_office_code = @numbers.shift(3).join
  end

  def extract_station_code
    @station_code = @numbers.join
  end
end
