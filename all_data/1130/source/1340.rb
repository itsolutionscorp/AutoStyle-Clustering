def combine_anagrams(words)	
	words = words.sort_by {|x| x.downcase.split(//).sort.to_s}
	c= words.sort_by {|x| x.downcase.split(//).sort.to_s}
	groupOfAnagrams = []
	anagram = []
	temp = Array.new
	
	if words.length==0
		#puts "false"
		return words
	else
		for i in 0..words.length-1
			temp[i] = words[i].downcase.split(//).sort.to_s
			if anagram.length == 0
				groupOfAnagrams = Array.new
				anagram.push(groupOfAnagrams)
				groupOfAnagrams.push(words[i])
			elsif temp[i] == groupOfAnagrams.first.downcase.split(//).sort.to_s
				groupOfAnagrams.push(words[i])
			elsif temp[i] != groupOfAnagrams.first.downcase.split.sort.to_s
				groupOfAnagrams = Array.new
				groupOfAnagrams.push(words[i])
				anagram.push(groupOfAnagrams)
			end
		end
		puts "#{anagram}"
		return anagram
	end
	
end

combine_anagrams([])