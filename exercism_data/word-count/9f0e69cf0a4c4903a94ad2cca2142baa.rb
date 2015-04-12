class Phrase

  def initialize(words)
    @words = cleaned_words(words)
  end

  def word_count
    @words.each_with_object({}) do |word, counts|
      counts[word] ? counts[word] += 1 : counts[word] = 1
    end
  end

  def cleaned_words(words)
    words.tr('^0-9A-Za-z, ', '').downcase.gsub(',', ' ').split
  end

end
