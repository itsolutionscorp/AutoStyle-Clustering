def combine_anagrams(words)
  list=[]
  words.each do |word|
  	list<<word.downcase.chars.sort.join
  end
  all={}
  index=0
  list.each do |word|
  	if !all[word] 
  	 	all[word]=[]
  	 end
  	all[word]<<index
  	index+=1
  end
  result=[]
  all.each_value do |value|
  	result << value.map{|index| words[index]}
  end
  result
end
