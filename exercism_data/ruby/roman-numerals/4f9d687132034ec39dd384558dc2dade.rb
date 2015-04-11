class Fixnum
  def to_roman
    thousands(self / 1000) + hundreds(self % 1000 / 100) + tens(self % 1000 % 100 / 10) + ones(self % 1000 % 100 % 10)
  end

  def thousands(number)
    'M' * number
  end

  def hundreds(number)
    if number == 4
      'CD'
    elsif number == 5
      'D'
    elsif number == 9
      'CM'
    elsif (6..8).to_a.include?(number)
      'D' + 'C' * (number - 5)
    elsif (1..3).to_a.include?(number)
      'C' * number
    else
      ''
    end
  end

  def tens(number)
    if number == 4
      'XL'
    elsif number == 5
      'L'
    elsif number == 9
      'XC'
    elsif (6..8).to_a.include?(number)
      'L' + 'X' * (number - 5)
    elsif (1..3).to_a.include?(number)
      'X' * number
    else
      ''
    end
  end

  def ones(number)
    if number == 4
      'IV'
    elsif number == 5
      'V'
    elsif number == 9
      'IX'
    elsif (6..8).to_a.include?(number)
      'V' + 'I' * (number - 5)
    elsif (1..3).to_a.include?(number)
      'I' * number
    else
      ''
    end
  end
end
