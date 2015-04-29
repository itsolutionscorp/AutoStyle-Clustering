class Fixnum
  def to_roman
    table_romans = [
      [1000, 'M'],
      [900, 'CM'],
      [500, 'D'],
      [400, 'CD'],
      [100, 'C'],
      [90, 'XC'],
      [50, 'L'],
      [40, 'XL'],
      [10, 'X'],
      [9, 'IX'],
      [5, 'V'],
      [4, 'IV'],
      [1, 'I']
    ]
    n = self
    i = 0
    roman = ""
    while n > 0
      if n >= table_romans[i][0]
        n -= table_romans[i][0]
        roman += table_romans[i][1]
      else
        i = i.next
      end
    end
    roman
  end
end
