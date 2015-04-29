class Hamming
  def self.compute(arg1,arg2)
    ham = 0
	a=*(0..arg1.length-1)
	  a.each do |i|
	    ham += 1 if arg1[i] != arg2[i]
	  end
	return ham
  end
end
