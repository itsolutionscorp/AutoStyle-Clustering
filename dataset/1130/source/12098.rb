class Hash
  def add_anagram(word)
    if self[word.downcase.chars.sort]
      self[word.downcase.chars.sort] << word
    else
      self[word.downcase.chars.sort] = [word]
    end
  end
end

def combine_anagrams(words)
  res = {}
  words.each{|x| res.add_anagram x}
  res.values
end
