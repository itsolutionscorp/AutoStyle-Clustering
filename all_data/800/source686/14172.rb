def combine_anagrams(words)
  hsh = {}
  words.each do |word|
  	index = word.downcase.split(//).sort().join
  	if !hsh[index]
  	  hsh[index] = []
  	end
  	hsh[index] << word
  end
  result = []
  hsh.each do |index, group|
  	result << group
  end
  result
end
