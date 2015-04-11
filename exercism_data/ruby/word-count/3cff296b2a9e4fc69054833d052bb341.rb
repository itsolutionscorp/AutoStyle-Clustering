module TextProcessor
  WORD_MATCHER = /\w+\b/

  def self.get_word_list(text)
    text.scan(WORD_MATCHER).map(&:downcase)
  end
end

Phrase = Struct.new(:text) do
  def word_count
    word_list.each_with_object(Hash.new(0)) do |word, word_counts|
      word_counts[word] = word_counts[word] + 1 
    end
  end

  private 
  
  def word_list
    TextProcessor.get_word_list(text)
  end
end
