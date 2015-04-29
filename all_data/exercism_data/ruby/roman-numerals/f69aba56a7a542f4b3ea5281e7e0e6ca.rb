class Integer

  def to_roman
    acc = ''
    return go(self, acc)
  end

  def go(i, acc)
    if i >= 1000
      go(i-1000, acc << 'M')
    elsif i >= 900
      go(i-900, acc << 'CM')
    elsif i >= 500
      go(i-500, acc << 'D')
    elsif i >= 400
      go(i-400, acc << 'CD')
    elsif i >= 100
      go(i-100, acc << 'C')
    elsif i >= 90
      go(i-90, acc << 'XC')
    elsif i >= 50
      go(i-50, acc << 'L')
    elsif i >= 40
      go(i-40, acc << 'XL')
    elsif i >= 10
      go(i-10, acc << 'X')
    elsif i >= 9
      go(i-9, acc << 'IX')
    elsif i >= 5
      go(i-5, acc << 'V')
    elsif i >= 4
      go(i-4, acc << 'IV')
    elsif i >= 1
      go(i-1, acc << 'I')
    else
      return acc
    end
  end
end
