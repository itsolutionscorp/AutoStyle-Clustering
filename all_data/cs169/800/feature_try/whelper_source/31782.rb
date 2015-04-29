def processWord(word)
  char_map = {}
  word.each_char do |c|
    if char_map.has_key?(c.downcase) then
      char_map[c.downcase] += 1
    else
      char_map[c.downcase] = 1
    end
  end
  return [word, word.size, char_map]
end

def compareProcessedWord(w1, w2)
  word_map1 = w1[2]
  word_map2 = w2[2]
  if (word_map1.keys.size != word_map2.keys.size) then
    puts("gg3")
    return false
  end
  word_map1.keys.each do |key1|
    if (word_map2.has_key?(key1) == false) then
      return false
      puts("gg1")
    end
    if (word_map1[key1] != word_map2[key1]) then
      return false
      puts("gg2")
    end
  end
  return true
end

def combine_anagrams(words)
  words_with_info = words.map { |element| processWord(element) }
  word_map = {}
  words_with_info.each do |word_with_info|
    if word_map.has_key?(word_with_info[1]) then
      word_map[word_with_info[1]].push(word_with_info)
    else
      word_map[word_with_info[1]] = [word_with_info]
    end
  end
  output = []
  word_map.each_key do |key|
    words_same_size = word_map[key]
    while (words_same_size.size > 0) do
      word = words_same_size.pop
      word_collection = [word[0]]
      i = (words_same_size.size - 1)
      while (i >= 0) do
        w = words_same_size[i]
        if compareProcessedWord(word, w) then
          word_collection.push(w[0])
          words_same_size.delete_at(i)
        end
        i = (i - 1)
      end
      output.push(word_collection)
    end
  end
  return output
end

