class Fixnum
  ROMAN_PAD = 4
  ROMANS    = [
    { level: 1000 , forward: 'M' , back: nil , symbol: 'M' },
    { level: 500  , forward: 'M' , back: 'C' , symbol: 'D' },
    { level: 100  , forward: 'D' , back: 'C' , symbol: 'C' },
    { level: 50   , forward: 'C' , back: 'X' , symbol: 'L' },
    { level: 10   , forward: 'L' , back: 'X' , symbol: 'X' },
    { level: 5    , forward: 'X' , back: 'I' , symbol: 'V' },
    { level: 1    , forward: 'V' , back: 'I' , symbol: 'I' },
  ]

  def to_roman
    roman(self)
  end

  private
  def roman(number)
    return "" if number <= 0

    level = ROMANS.detect { |level| !(number < level[:level]) }

    left = number % level[:level]
    unit = number.to_s.gsub(/\d/, "0").sub("0","1").to_i
    nine = level[:level] + unit * ROMAN_PAD

    draw_roman = -> (number) { "%s%s%s" % [level[:back], level[:forward], roman(number)] }

    if number >= nine
      draw_roman.(number - nine)
    elsif number < level[:level] * ROMAN_PAD
      pieces = number / level[:level]
      "%s%s" % [level[:symbol] * pieces, roman(left)]
    else
      draw_roman.(left)
    end
  end
end
