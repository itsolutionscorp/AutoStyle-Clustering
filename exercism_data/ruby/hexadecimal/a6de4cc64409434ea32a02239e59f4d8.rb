class Hexadecimal
  HEXITS = "0123456789abcdef"

  def initialize(hexits)
    @hexits = hexits
  end

  def to_decimal
    return 0 unless valid?
    @hexits.chars.reduce(0) { |value, hexit|
      value * 16 + value(hexit)
    }
  end

  def valid?
    @hexits.chars.all? { |hexit| value(hexit) }
  end

  private

  def value(hexit)
    HEXITS.index(hexit)
  end
end
