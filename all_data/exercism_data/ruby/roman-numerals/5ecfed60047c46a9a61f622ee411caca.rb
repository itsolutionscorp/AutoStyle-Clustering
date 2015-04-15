NUMERALS = [
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
    [1, 'I'],
]

class Integer < Numeric
  def to_roman
    arabic = self.to_i
    roman = ''
    
    NUMERALS.each do |arab, rom|
      while arabic >= arab
        roman += rom
        arabic -= arab
      end
      
      break if arabic <= 0
    end
    
    roman
  end
end
