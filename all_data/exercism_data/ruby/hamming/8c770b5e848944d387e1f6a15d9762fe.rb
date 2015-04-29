class Hamming
	def self.compute(arg, arg2)

	  a = arg.chars
	  b = arg2.chars
    combo =[]
    combo.push(a)
		combo.push(b)
		num = 0
		combo[0].length.times do |i|
			combo[0][i] != combo[1][i] ? num+=1 : num+=0
		end


	 return num
	end
end
