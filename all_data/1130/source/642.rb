def combine_anagrams(words)
	res = []
	words.each do |w|
		res.push(words.select {|v| v.sort.eql?(w.sort)})
	end
	res.uniq
end

class String
	def sort
		res = ""
		self.downcase.split('').sort.each do |l|
			res += l
		end
		res
	end
end