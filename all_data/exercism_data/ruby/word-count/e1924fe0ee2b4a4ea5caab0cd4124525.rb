module TextProcessor
  PUNCTUATION_MATCHER = /[^\w\s]/

  def strip_punctuation
    text.gsub(PUNCTUATION_MATCHER, '')
  end
end

Phrase = Struct.new(:text) do
  include TextProcessor

  def word_count
    downcased_words.each_with_object({}) do |word, word_counts|
      word_counts[word] = word_counts[word].to_i + 1 
    end
  end

  private 
  
  def downcased_words
    strip_punctuation.downcase.split(" ")
  end
end
