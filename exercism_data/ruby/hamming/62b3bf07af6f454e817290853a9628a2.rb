class Hamming

	def self.compute(str1, str2)
		str1 = str1.scan(/\w/)
		str2 = str2.scan(/\w/)

		cont = 0
		str1.size.times { |i| if str1[i] != str2[i] then cont += 1 end }
		return cont
	end

end
