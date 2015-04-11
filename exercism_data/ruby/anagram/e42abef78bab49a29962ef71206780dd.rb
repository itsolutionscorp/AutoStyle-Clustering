class Anagram
  def initialize word
    @word = word.to_s.downcase
  end

  def processed_word
    @processed_word ||= @word.chars.to_a.sort 
  end

  def is_anagram word
    processed_word == word.downcase.chars.to_a.sort   
  end

  def is_same_word word
    @word == word.downcase
  end

  def match words
    words.each_with_object([]) do |word, anagram_list|
      next if is_same_word word 
      if is_anagram word
        anagram_list << word
      end
    end
  end
end
