class PhoneNumber
  def initialize(phone_number)
    @raw = phone_number
    @digits = @raw.gsub(/\D/, "").chars

    setup
  end

  def area_code
    @digits[0, 3].join
  end

  def number
    @digits.join
  end

  def to_s
    "(#{@digits[0, 3].join}) #{@digits[3, 3].join}-#{@digits[6, 4].join}"
  end

  private

  def invalid_length?
    case
    when @raw.gsub(/\W/, "") != @digits.join
      true
    when @digits.length < 10
      true
    when @digits.length > 11
      true
    else
      false
    end
  end

  def invalid_first_number?
    @digits.length == 11 and @digits.first != '1'
  end

  def valid?
    case
    when invalid_length?, invalid_first_number?
      false
    else
      true
    end
  end

  def setup
    @digits = ("0" * 10).chars unless valid?
    @digits.shift if @digits.length == 11
  end
end
