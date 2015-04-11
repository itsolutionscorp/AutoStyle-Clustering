class Anagram
  def initialize word
    @source_word = word.to_s.downcase
    @source_chars = @source_word.chars
  end

  def match word_list
    word_list.select { |word| anagram?(word) }
  end

  private
  
  def anagram? word
    word = word.downcase

    if word == @source_word
      false
    else
      @source_chars.sort == word.chars.sort
    end
  end
end
