def combine_anagrams(words)
	pos={}
	output=[]
	words.each do |x|
		anagram=x.downcase.unpack('c*').sort.pack('c*')
		unless pos.has_key?(anagram)
			pos[anagram]=output.length
			output << []
		end
		output[pos[anagram]] << x
	end
	output
end

