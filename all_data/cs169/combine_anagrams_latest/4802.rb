def combine_anagrams(words)
  # remove any duplicates from word list
  words = remove_duplicates(words)
  anagrams = []
  # iterate through array for each word, building an array of anagrams
  words.each do |word|
    anagram_array = []
    anagram_array << word
    words.each do |test|
      if word == test || word.downcase.chars.sort.join != test.downcase.chars.sort.join
        next
      else
        anagram_array << test
      end
    end
    # test to see if similar anagram array has already been found
    if not_in_array(anagram_array, anagrams)
      anagrams << anagram_array
    end
  end
  return anagrams
end

def not_in_array(test_array, full_array)
  full_array.each do |test|
    if test[0].downcase.chars.sort.join == test_array[0].downcase.chars.sort.join
      return false
    end
  end
  return true
end

def remove_duplicates(words)
  new_words = []
  words.each do |word|
    unless new_words.index(word)
      new_words << word
    end
  end
  return new_words
end