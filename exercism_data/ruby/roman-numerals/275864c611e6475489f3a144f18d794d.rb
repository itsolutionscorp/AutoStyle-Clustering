class Fixnum

  # f = first
  # s = second
  # t = third
  NUMERALS = {
    '0' => '',
    '1' => 'f',
    '2' => 'ff',
    '3' => 'fff',
    '4' => 'fs',
    '5' => 's',
    '6' => 'sf',
    '7' => 'sff',
    '8' => 'sfff',
    '9' => 'ft'
  }

  def to_roman
    thousands((self / 1000).last_digit) +
      hundreds((self / 100).last_digit) +
      tens((self / 10).last_digit) +
      ones(self.last_digit)
  end

  def last_digit
    self.to_s[-1, 1]
  end

  private

  # M = 1000
  def thousands(t)
    convert t, 'M', '', ''
  end

  # D = 500
  # C = 100
  def hundreds(h)
    convert h, 'C', 'D', 'M'
  end

  # L = 50
  # X = 10
  def tens(t)
    convert t, 'X', 'L', 'C'
  end

  # V = 5
  # I = 1
  def ones(o)
    convert o, 'I', 'V', 'X'
  end

  def convert(key, first, second, third)
    NUMERALS[key].gsub('f',first).gsub('s',second).gsub('t',third)
  end

end
