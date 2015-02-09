def combine_anagrams(words)
  results = Hash.new
  sorted_words = words.map {|word| word.downcase.chars.sort.join}
  uniq_sorted_words = sorted_words.uniq
  uniq_sorted_words.each {|word| results[word]=[]}
  results.each_key {|key| words.each {|word| if word.downcase.chars.sort.join==key 
                                               results[key] << word 
                                             end}}
  results_a = []
  results.each_value {|value| results_a << value}
  results_a
end
