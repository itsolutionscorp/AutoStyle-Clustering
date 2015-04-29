class Anagram
  def initialize(target_word)
    @target_word = target_word
    @target_chars = sort_characters(@target_word)
  end

  def match(words)
    words.find_all{|w| sort_characters(w) == @target_chars}
         .reject{|w| w.downcase == @target_word.downcase}
  end

  private
    def sort_characters(word)
      word.downcase.chars.sort
    end
end
