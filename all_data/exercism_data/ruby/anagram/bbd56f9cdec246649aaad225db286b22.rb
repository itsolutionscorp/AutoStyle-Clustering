class Anagram
  def initialize word
    @word = word.to_s.downcase
  end

  def match words
    words.each_with_object([]) do |word, anagram_list|
      next if is_same_word? word or word.nil? 
      if is_anagram? word
        anagram_list << word
      end
    end
  end

private
  def process_word word
    word.chars.sort 
  end

  def processed_word
    @processed_word ||= process_word @word
  end

  def is_anagram? word
    processed_word == (process_word word.downcase)
  end

  def is_same_word? word
    @word == word.downcase
  end
end
