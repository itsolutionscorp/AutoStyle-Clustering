def combine_anagrams(words)
  return words if (words.length == 0)
  anagram_list = []
  print(words)
  puts
  words = words.flatten
  words.each do |word|
    if (anagram_list.length == 0) then
      (anagram_list << [word])
      next
    end
    sorted_word = word.downcase.scan(/./).sort.join
    print(sorted_word)
    inserted = false
    anagram_list.each_index do |i|
      print(" ")
      print(anagram_list[i][0].downcase.scan(/./).sort.join)
      print("\n")
      if (anagram_list[i][0].downcase.scan(/./).sort.join == sorted_word) then
        (anagram_list[i] << word)
        inserted = true
        break
      end
    end
    (anagram_list << [word]) unless inserted
  end
  return anagram_list
end