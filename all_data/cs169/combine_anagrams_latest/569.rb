def combine_anagrams(words)
  hash = Hash.new
  words.each { |word| key = word.downcase.chars.sort.join; 
     if hash.has_key?(key) 
	    hash[key] << word;
     else 
	    hash[key] = [word]; 
	 end } 
  out = [];
  hash.each { |key, value| out << value};
  out
end
   