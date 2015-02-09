test_case = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

def combine_anagrams(words)
  @word_hash = Hash.new(0)
  words.each do |word|
    @sorted_word = word.downcase.chars.sort.join
    if @word_hash.key?(@sorted_word)
      @word_hash[@sorted_word].push(word)
    else
      @word_hash[@sorted_word] = [ word ]
    end
  end
  
  @word_list = []
  @word_hash.each do |list|
    @word_list.push(list[1].sort)
  end
  return @word_list
end

