class Integer
  def to_roman
    Roman.new(self).get_numerals
  end
end

class Roman
  def initialize(number)
    @num_remaining = number
  end

  def get_numerals
    thousands = calculate_thousands
    hundreds  = calculate_hundreds
    tens      = calculate_tens
    ones      = calculate_ones
    thousands + hundreds + tens + ones
  end

  private
  attr_reader :num_remaining

  def calculate_thousands
    'M' * thousands
  end

  def calculate_hundreds
    calculate_hundreds_remaining
    case
    when num_remaining >= 900 then 'CM'
    when num_remaining >= 500 && num_remaining < 900 then 'D' + 'C' * (hundreds - 5)
    when num_remaining >= 400 && num_remaining < 500 then 'CD'
    else 'C' * hundreds
    end
  end

  def calculate_tens
    calculate_tens_remaining
    case
    when num_remaining >= 90 then 'XC'
    when num_remaining >= 50 && num_remaining < 90 then 'L' + 'X' * (tens - 5)
    when num_remaining >= 40 && num_remaining < 50 then 'XL'
    else 'X' * tens
    end
  end

  def calculate_ones
    calculate_ones_remaining
    case
    when num_remaining == 9 then 'IX'
    when num_remaining >= 5 && num_remaining < 9 then 'V' + 'I' * (ones - 5)
    when num_remaining == 4 then 'IV'
    else 'I' * ones
    end
  end

  def calculate_hundreds_remaining
    @num_remaining -= thousands * 1000
  end

  def calculate_tens_remaining
    @num_remaining -= hundreds * 100
  end

  def calculate_ones_remaining
    @num_remaining -= tens * 10
  end

  def thousands
    num_digits(1000)
  end

  def hundreds
    num_digits(100)
  end

  def tens
    num_digits(10)
  end

  def ones
    num_remaining
  end

  def num_digits(digit_value)
    num_remaining / digit_value
  end
end
