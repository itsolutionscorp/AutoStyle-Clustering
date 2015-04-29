def combine_anagrams(words)

#  	arrWord = words.scan(/[A-Z]|[a-z]|,/)
# 	arrClean =  arrWord[0,arrWord.length].map { |i| i.to_s }.join("").split(',') 
	arrSort = words.collect{ |str| str.chars.sort { |a, b| a.casecmp(b) }.join }
	
	result = Hash.new{|h, k| h[k] = []}
	i = 0
	while i < words.length
		newStr = arrSort[i]
		temp = newStr.downcase
		result[temp] << words[i]
		i += 1
	end
	
	output = Array.new
	result.sort_by {|a,b| b}.reverse.each { |elem| elem[0] }
	
	return result.values
end