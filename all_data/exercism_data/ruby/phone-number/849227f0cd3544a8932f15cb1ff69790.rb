class PhoneNumber
  attr_reader :number

  def initialize(phone)
    @number = clean phone
  end

  def area_code
    number[0, 3]
  end

  def to_s
    format '(%s) %s-%s', area_code, number[3, 3], number[6, 4]
  end

  private

  FORMATS = Regexp.union(/\A1?(\d{10})\z/,
                         /\A1?\((\d{3})\) (\d{3})-?(\d{4})\z/,
                         /\A1?(\d{3})\.(\d{3})\.(\d{4})\z/)

  def clean(phone)
    FORMATS.match(phone) { |m| return m.captures.join }
    '0000000000'
  end
end
