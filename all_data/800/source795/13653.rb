def combine_anagrams(words)
  #reorder each word into a new array 'a'
  a = words.map{|i| i.downcase.chars.sort.join}
  #remove repeated words in a and save the new array into 'b'
  b = a.uniq
  #iterate over a and save index of each items into a hash table
  htable = Hash.new
  #initalize htable's values to arrays
  b.each{|w| htable[w] = []}
  b.each{|w| a.each_with_index{|x,y| htable[w] << y if x == w}}
  #create a new array 'result' to save final result and a temparory array 't' 
  result = []
  t = []
  htable.each do |x, y|
	y.each{|i| t << words[i]}
	result << t
	t = []
  end
  return result
end

#s1 = %w[cars, for, potatoes, racs, four, scar, creams, scream]
#print combine_anagrams(s1)