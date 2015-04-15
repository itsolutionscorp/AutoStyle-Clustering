class Fixnum
  MAP = { 1 => 'I', 5 => 'V', 10 => 'X',
    50 => 'L', 100 => 'C', 500 => 'D', 1000 => 'M' }

  PREV = { 5 => 1, 10 => 1, 100 => 10, 50 => 10, 500 => 100, 1000 => 100 }

  def to_roman
    convert(self)
  end

  def convert(num)
    return MAP[num] if MAP.keys.include?(num)
    return '' unless num > 0
    multi = 10**Math.log10(num).to_i
    if num.between?(1, 5) || num.between?(11, 50) || num.between?(101, 500)
      guard, key, max = [4, 1, 5].map { |n| n * multi }
    elsif num.between?(6, 10) || num.between?(51, 100) || num.between?(501, 999)
      guard, key, max = [9, 5, 10].map{ |n| n * multi }
    else
      return MAP[1000] + convert(num - 1000)
    end
    if num < guard
      MAP[key] + convert(num - key)
    else
      MAP[PREV[max]] + MAP[max] + convert(num - (max - PREV[max]))
    end
  end
end
