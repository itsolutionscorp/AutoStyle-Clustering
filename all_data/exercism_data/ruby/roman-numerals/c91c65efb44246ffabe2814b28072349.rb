class Fixnum
  def to_roman
    self.to_s.split('').reverse.each_with_index.inject('') {|sum, group| roman_helper(group[0].to_i, group[1]).to_s + sum }
  end

  def roman_helper(num, index)
    low, mid, high = 'IXCM'[index].to_s, 'VLD'[index].to_s, 'XCM'[index].to_s
    case num
      when 1, 2, 3
        low * num
      when 4
        low + mid
      when 5
        mid
      when 6, 7, 8
        mid + low * (num - 5)
      when 9
        low + high
      else
        ''
    end
  end
end
