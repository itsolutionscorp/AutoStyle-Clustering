class Fixnum
  def to_roman
    throw ArgumentError if self > 3000
    converter = [[1000, 'M'], [900, 'CM'], [500, 'D'], [400, 'CD'], [100, 'C'],
                [90, 'XC'], [50, 'L'], [40, 'XL'], [10, 'X'], [9, 'IX'],
                [5, 'V'], [4, 'IV'], [1, 'I']]
    number = self
    roman = ''
    converter.each do |tuple|
      while number - tuple[0] >= 0
        roman += tuple[1]
        number -= tuple[0]
      end
    end
    roman
  end
end
