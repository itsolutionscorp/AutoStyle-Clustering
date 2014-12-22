def combine_anagrams(words)
  array = []
  hash = {}
  words.each do |word|
    sorted_word = word.downcase.chars.sort.join
    print((((("word=>" + word) + " sorted_word=>") + sorted_word) + "\n"))
    if (hash[sorted_word] == nil) then
      hash[sorted_word] = array.size
      (array << [word])
    else
      (array[hash[sorted_word]] << word)
    end
  end
  return array
end

