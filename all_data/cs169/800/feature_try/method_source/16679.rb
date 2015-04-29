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