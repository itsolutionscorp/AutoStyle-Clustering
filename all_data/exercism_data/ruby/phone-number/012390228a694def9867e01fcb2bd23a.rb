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
    numbers = value.gsub(/\D/, "").chars

    if numbers.size == 11 and numbers.first == "1"
      numbers.shift
    elsif numbers.size != 10
      numbers = ["0"] * 10
    end

    @area_code           = numbers.shift(3).join
    @central_office_code = numbers.shift(3).join
    @station_code        = numbers.join
  end
end
