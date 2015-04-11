class Anagram

  def initialize word
    @source_word = word
  end

  def match list
    list.select { |word| word.is_anagram_of? @source_word }
  end

end

class String

  def is_anagram_of? word
    char_hash == word.char_hash && downcase != word.downcase
  end

  def char_hash
    hash = Hash.new 0
    downcase.chars.each { |char| hash[char] += 1 }
    hash
  end

end
