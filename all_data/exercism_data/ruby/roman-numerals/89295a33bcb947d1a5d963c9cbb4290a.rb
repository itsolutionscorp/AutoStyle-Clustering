class Fixnum

  ROM_CONVERSION = [
    ['M', 1000],
    ['CM', 900],
    ['D', 500],
    ['CD', 400],
    ['C', 100],
    ['XC', 90],
    ['L', 50],
    ['XL', 40],
    ['X', 10],
    ['IX', 9],
    ['V', 5],
    ['IV', 4],
    ['I', 1]
  ]

  def to_roman(value = self, roman = '', i=0)
    until value <= 0
      roman << ROM_CONVERSION[i][0]*(value/(ROM_CONVERSION[i][1]))
      value -= ROM_CONVERSION[i][1]*(value/(ROM_CONVERSION[i][1]))
      i+=1
    end
    return roman
  end

end
