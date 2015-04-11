class Phone < Struct.new(:phone_string)

  NORMAL_SIZE = 10

  def number
    if valid?
      digits.last(NORMAL_SIZE).join('')
    else
      '0' * NORMAL_SIZE
    end
  end

  def area_code
    number[0..2]
  end

  def to_s
    Formatter.new('(xxx) xxx-xxxx').format(number)
  end

  def valid?
    digits.size == NORMAL_SIZE || valid_eleven_digits_phone?
  end

private

  def digits
    @digits ||= phone_string.gsub(/\D+/, '').chars
  end

  def valid_eleven_digits_phone?
    digits.size == 11 && digits.first == '1'
  end
end

class Formatter < Struct.new(:pattern)

  def format(text)
    current_char = -1
    pattern.gsub('x') do
      current_char += 1
      text[current_char]
    end
  end
end
