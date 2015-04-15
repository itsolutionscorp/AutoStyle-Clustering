class Fixnum
  def to_roman
    number = self
    arabic_numbers = [1000, 900, 500, 400,  100, 90,   50,  40,   10,  9,    5,   4,     1 ]
    roman_numbers  = ['M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I']
    final_roman = ''

    until number == 0
      arabic_numbers.each_with_index do |an, i|
        if number >= an
          final_roman << roman_numbers[i]
          number -= an
          break
        end
      end
    end
    final_roman
  end
end
