def combine_anagrams(words)
  anagram_list = Array.new
  while ((w1 = words.pop) != nil)
    tmp_group = Array.new
    tmp_group << w1
    # Check rest of list for anagrams of w1
    words.each do |w2|
      if are_anagrams?(w1,w2)
        tmp_group << w2
        words.delete(w2)
      end
    end
    anagram_list << tmp_group
  end
  anagram_list
end

def are_anagrams?(word1, word2)
  word1.downcase.scan(/./).sort == word2.downcase.scan(/./).sort
end

def test_anagrams()
  input = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']
  combine_anagrams(input)
end
