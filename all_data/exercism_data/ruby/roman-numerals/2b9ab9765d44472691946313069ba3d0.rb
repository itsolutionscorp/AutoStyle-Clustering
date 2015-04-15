class Fixnum
  NUM_TO_ROMAN = {
    1000 => 'M',
    500 => 'D',
    100 => 'C',
    50 => 'L',
    10 => 'X',
    5 => 'V',
    1 => 'I'
  }

  def to_roman
    return "" if self == 0
    if NUM_TO_ROMAN[first_digit_value]
      return NUM_TO_ROMAN[first_digit_value] + \
        (self - first_digit_value).to_roman
    end

    NUM_TO_ROMAN.keys.each do |value|
      complement = (NUM_TO_ROMAN.keys.each
                    .select { |k| value - k == first_digit_value }).max
      unless complement.nil?
        return complement.to_roman \
        + value.to_roman \
        + (self - (value - complement)).to_roman
      end

      return value.to_roman + (self - value).to_roman if value < self
    end
  end

  private
  def first_digit_value
    position = self.to_s.size-1
    position_value = 10**position
    first_digit = self.to_s.slice(0).to_i
    first_digit*position_value
  end
end
