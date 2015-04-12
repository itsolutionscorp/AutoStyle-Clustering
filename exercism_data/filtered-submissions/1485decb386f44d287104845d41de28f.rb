def compute(str1, str2)
    str1[0..(str2.length - 1)].each_char.map.with_index do |symbol, i|
      symbol == str2[i] ? 0 : 1
    end.inject(:+).to_i
  end