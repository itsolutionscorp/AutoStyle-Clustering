# Convert to roman number
class Integer
  def to_roman
    out = []
    num = self
    map = {
      1000 => 'M',
      900 => 'CM',
      500 => 'D',
      400 => 'CD',
      100 => 'C',
      90 => 'XC',
      50 => 'L',
      40 => 'XL',
      10 => 'X',
      9 => 'IX',
      5 => 'V',
      4 => 'IV',
      1 => 'I',
    }
    map.keys.each do |match|
      n_of_times, rest = num.divmod(match)
      out.push map[match] * n_of_times
      num = rest
    end
    out.join
  end
end
