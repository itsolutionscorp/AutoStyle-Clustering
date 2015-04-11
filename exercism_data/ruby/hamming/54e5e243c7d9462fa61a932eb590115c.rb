class Hamming
  def self.compute(code1, code2)
  res = code1.split("").zip(code2.split("")).reduce(0) { |acc, item|
  	if item[0] != item[1] && !item[0].nil? && !item[1].nil? 
      acc = acc + 1
  	end
    acc
  }    
  end
end
