ROMAN_HASH = {I: 1, V: 5, X: 10, L: 50, C: 100, D: 500, M: 1000}

public

def to_roman
  digit_array = self.to_s.split('').map { |digit| digit.to_i }.reverse
  romanizing_block = Proc.new {|digit, index| digit.digitize(1 * 10 ** index, 5 * 10 ** index, 10 * 10 ** index)} 
  digit_array.map.each_with_index(&romanizing_block).reverse.join
end

def digitize(one, five, ten)
  digit = self 
  if digit < 4
    digit.single_make(one)
  elsif digit == 5
    1.single_make(five)
  elsif digit == 9
    9.surround_x(ten, one)
  else 
    digit.surround_x(five, one)
  end
end

def single_make(single_type)
  ROMAN_HASH.key(single_type).to_s * self
end

def surround_x(x, surrounder)
  diff = self * surrounder - x
  diff_digits = diff.to_s.split('').map {|d| d.to_i}
  if diff < 0
    1.single_make(surrounder) + 1.single_make(x)
  else 
    1.single_make(x) + diff_digits[0].single_make(surrounder)
  end
end
