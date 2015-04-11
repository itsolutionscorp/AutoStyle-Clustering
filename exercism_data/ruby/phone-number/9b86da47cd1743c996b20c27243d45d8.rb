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

  def valid?
    case
    when @raw.gsub(/\W/, "") != @digits.join
      false
    when @digits.length < 10
      false
    when @digits.length > 11
      false
    when (@digits.length == 11 and @digits.first != '1')
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
