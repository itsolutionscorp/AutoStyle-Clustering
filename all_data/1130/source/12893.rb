def combine_anagrams(words)
  groups = {}
  words.each do |word|
    lowercase = word.downcase
    chars_in_word = []
    lowercase.each_char {|x| chars_in_word = chars_in_word+[x]}
    key = chars_in_word.sort
    if (groups[key] == nil)
    groups[key] = []
    end
    groups[key] = groups[key]+[word]
  end
  groups.values
end
