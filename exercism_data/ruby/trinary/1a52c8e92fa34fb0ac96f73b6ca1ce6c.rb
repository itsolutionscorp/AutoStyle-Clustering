class Trinary
  def initialize(trinary_string)
    @trinary_string = trinary_string
  end

  def to_decimal
    @decimal ||= make_decimal
  end

  private

  def make_decimal
    decimal = 0
    if valid?
      @trinary_string.chars.reverse.each_with_index do |digit, i|
        decimal += digit.to_i * 3**i
      end
    end

    decimal
  end

  def valid?
    !!@trinary_string.match(/^[012]+$/)
  end

end
