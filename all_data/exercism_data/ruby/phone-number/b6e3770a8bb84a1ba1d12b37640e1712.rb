class Phone
  def initialize(phone_number)
    @raw_phone_number = phone_number
  end

  def number
    number = @raw_phone_number.gsub(/\D/, "")
    if number.match(/\A1\d{10}\Z/)
      number[1..-1]
    elsif number.match(/\A\d{10}\Z/)
      number
    else
      '0' * 10
    end
  end

  def area_code
    number[0..2]
  end

  def to_s
    number.match(/(\d{3})(\d{3})(\d{4})/)
    "(#{$1}) #{$2}-#{$3}"
  end

  private

  def valid?
    number != '0' * 10
  end
end
