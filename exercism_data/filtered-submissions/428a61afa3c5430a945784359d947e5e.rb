def compute(code1, code2)
  code1 = Array.new([code1.size, code2.size].max) { |i| code1[i] }
  code2 = Array.new([code1.size, code2.size].max) { |i| code2[i] }
  res = code1.zip(code2).reduce(0) { |acc, item|
  	if item[0] != item[1] && !item[0].nil? && !item[1].nil?
      acc = acc + 1
  	end
    acc
  }
  end