class Integer
  @@roman_symbols = [
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
  def to_roman
    decimal = self
    @@roman_symbols.inject('') do |out, symbol|
      while (decimal - symbol[0]) >= 0
        decimal = decimal - symbol[0]
        out += symbol[1]
      end
      out
    end
  end  
end
