class Fixnum
  ROMAN_PAD = 4
  ROMANS    = [
    { level: 1    , forward: 'V' , back: 'I' , symbol: 'I' },
    { level: 5    , forward: 'X' , back: 'I' , symbol: 'V' },
    { level: 10   , forward: 'L' , back: 'X' , symbol: 'X' },
    { level: 50   , forward: 'C' , back: 'X' , symbol: 'L' },
    { level: 100  , forward: 'D' , back: 'C' , symbol: 'C' },
    { level: 500  , forward: 'M' , back: 'C' , symbol: 'D' },
    { level: 1000 , forward: 'M' , back: nil , symbol: 'M' }
  ]

  def to_roman
    calculate_roman(self)
  end

  private
  def roman_base(number)
    ("1" + ("0" * (number.to_s.size - 1))).to_i
  end

  def calculate_roman(number)
    return "" if number <= 0

    str = ""

    ROMANS.reverse.each do |level|
      next if number < level[:level]

      nine_case = level[:level] + roman_base(number) * ROMAN_PAD
      pieces = number / level[:level]
      remain = number % level[:level]

      if number >= nine_case
        str << "%s%s" % [level[:back], level[:forward]]
        str << calculate_roman(number - nine_case)
      elsif number < level[:level] * ROMAN_PAD
        str << level[:symbol] * pieces
        str << calculate_roman(remain)
      else
        str << "%s%s" % [level[:back], level[:forward]]
        str << calculate_roman(remain)
      end

      break
    end

    str
  end
end
