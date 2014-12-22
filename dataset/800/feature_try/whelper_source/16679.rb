def combine_anagrams(words)
  list = Array.new
  words.each do |word|
    match = nil
    list.each do |anagram|
      match = list.index(anagram) if matches?(word, anagram[0])
    end
    if (not match) then
      puts((("Word " + word) + " doesnt match"))
      item = [word]
      list.push(item)
    else
      puts((("Word " + word) + " matches"))
      list[match].push(word)
    end
  end
  return list
end

def matches?(string1, string2)
  word1 = string1.downcase.chars.sort.join
  word2 = string2.downcase.chars.sort.join
  return word1.eql?(word2)
end

