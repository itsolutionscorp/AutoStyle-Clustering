module RomanNumerals
  KEY = {
    1000 => 'M',
    900  => 'CM',
    500  => 'D',
    400  => 'CD',
    100  => 'C',
    90   => 'XC',
    50   => 'L',
    40   => 'XL',
    10   => 'X',
    9    => 'IX',
    5    => 'V',
    4    => 'IV',
    1    => 'I'
  }

  def to_roman(result = '')
    return result << '' if lower_limit?
    return self if  upper_limit?

    return result << KEY[self] if done?
    key = select_key[0]
    result << KEY[key] && (self - key).to_roman(result)
  end

  private

  def select_key
    KEY.select { |k, _| k <= self }.to_a[0]
  end

  def done?
    self == 1
  end

  def lower_limit?
    self <= 0
  end

  def upper_limit?
    self >= 4000
  end
end
Fixnum.include(RomanNumerals)
