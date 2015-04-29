class Hamming
	def self.compute(text,name)
		array1 = text.split(//)
		array2 = name.split(//)
		num = 0
		array1.zip(array2).each do |t,n|
			if t != n
				num +=1
			end
		end
		return num
	end
end
