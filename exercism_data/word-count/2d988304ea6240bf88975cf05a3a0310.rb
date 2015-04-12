module TextProcessor
  PUNCTUATION_MATCHER = /[^\w\s]/

  def strip_punctuation
    text.gsub(PUNCTUATION_MATCHER, '')
  end
end

Phrase = Struct.new(:text) do
  include TextProcessor

  def word_count
    strip_punctuation.downcase.split(" ").each_with_object({}) do |word, word_counts|
      word_counts[word] = word_counts[word].to_i + 1 
    end
  end
end
