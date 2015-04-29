def combine_anagrams_2(words)

  result = [];
  currentAnagrams = [];
  currentSortedWord = ""
  localWords = words.clone;
  
  words.each do |word|
    if( localWords.include? word)
      currentSortedWord = sort_word_ci(word);
      currentAnagrams = words.select {|x| sort_word_ci(x).casecmp(currentSortedWord) == 0}
      result.push(currentAnagrams)
      localWords.delete_if {|x| currentAnagrams.include? x}
    end
  end
  return result
end


def sort_word_ci(word)
  return word.chars.sort { |a, b| a.casecmp(b) }.join
end


def combine_anagrams(words)
  result = []
  currentAnagram = ""
  currentSortedword = ""
  
  until words.empty? do
    currentSortedWord = sort_word_ci(words[0]);
    currentAnagrams = words.select {|x| sort_word_ci(x).casecmp(currentSortedWord) == 0}
    result.push(currentAnagrams)
    words.delete_if {|x| currentAnagrams.include? x}
  end
  return result
end


words = ["hi", "ih", "mate", "tame", "lol", "HI", "oll", "LoL", "lool"];

combine_anagrams_2(words).each do |word|
  puts "Anagrams"
  puts word
end

puts "***********"
puts words

