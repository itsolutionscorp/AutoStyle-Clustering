def compute(first, second)
  	second.chars.each_with_index.map{|char, index| first.chars[index] == char ? 0 : 1}.reduce(:+)
  end