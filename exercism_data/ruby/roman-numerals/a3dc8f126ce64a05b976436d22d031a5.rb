class Integer
  def to_roman
    table = {1 => 'I', 4 => 'IV', 5 => 'V', 9 => 'IX',
             10 => 'X', 40 => 'XL', 50 => 'L', 90 => 'XC',
             100 => 'C', 400 => 'CD', 500 => 'D', 900 => 'CM',
             1000 => 'M'}
    str = ""
    s = self
    table.each_key.sort.reverse.each do |num|
      div = s / num
      if div > 0
        s = s % num
        str << table[num] * div
      end
    end
    return str
  end
end
