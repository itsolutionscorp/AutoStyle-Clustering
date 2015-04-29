class Fixnum
  def to_roman
    array = self.to_s.split('')
    i = roman_helper(array[-1].to_i, 'I', 'V', 'X')
    x = roman_helper(array[-2].to_i, 'X', 'L', 'C')
    c = roman_helper(array[-3].to_i, 'C', 'D', 'M')
    m = roman_helper(array[-4].to_i, 'M', '', '')
    m + c + x + i
  end

  def roman_helper(num, low, mid, high)
    case num
      when 1, 2, 3
        low * num
      when 4
        low + mid
      when 5
        mid
      when 6
        mid + low
      when 7
        mid + low * 2
      when 8
        mid + low * 3
      when 9
        low + high
      else
        ''
    end
  end
end
