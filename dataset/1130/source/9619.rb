def combine_anagrams(words)
  group = Array.new
  words.each do |word|
    i = true
    group.each_index do |groupKey|
      if sort(group[groupKey][0]) == sort(word)
        group[groupKey] = group[groupKey] + Array[word]
        i = false
      end
    end
    if i
      group = group + Array[Array[word]]
      i = false
    end
  end
  return group
end

def sort(word)
  word = word.downcase.chars.sort.join
  return word
end
