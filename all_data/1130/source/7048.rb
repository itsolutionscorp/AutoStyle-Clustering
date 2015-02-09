def combine_anagrams(words)
  @sortedWords = []
  words.each{ |word| @sortedWords[@sortedWords.length] = word.chars.sort.join  }
  
  @hashWords = {}
  @position = -1;
  @sortedWords.each { |word| if @hashWords.has_key?(word) then @hashWords[word].push(words[@position+=1])  else @hashWords[word] = [words[@position+=1]] end }
  
  return @hashWords.values.to_s
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
