def combine_anagrams(words)
  #   <YOUR CODE HERE>
  @words_hash = Hash.new
  words.each do |word|
  	sorted = word.downcase.chars.sort
  	if @words_hash.has_key?(sorted)
  		@words_hash[sorted] << word
  	else
  		@words_hash[sorted] = [word]
  	end
  end
  @words_hash.values
end