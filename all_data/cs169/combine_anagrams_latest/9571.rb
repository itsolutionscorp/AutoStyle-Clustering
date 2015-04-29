
def combine_anagrams(string)
  sorted_words = []
  string.each { |w| sorted_words.push(w.downcase.chars.sort.join) }
     
  anagrams = Hash.new()
  string.each_index do |index|
    key = sorted_words[index]
    if (anagrams.has_key?(key))
      anagrams[key].push(string[index])
    else
      anagrams[key] = [ string[index] ]
    end
  end
  
  anagrams.values
end

