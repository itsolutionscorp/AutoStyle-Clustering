module TextProcessor
  WORD_MATCHER = /\w+/

  def self.get_word_list(text)
    text.downcase.scan(WORD_MATCHER)
  end
end

Phrase = Struct.new(:text) do
  def word_count
    word_list.each_with_object(Hash.new(0)) do |word, word_counts|
      word_counts[word] += 1 
    end
  end

  private 
  
  def word_list
    TextProcessor.get_word_list(text)
  end
end
