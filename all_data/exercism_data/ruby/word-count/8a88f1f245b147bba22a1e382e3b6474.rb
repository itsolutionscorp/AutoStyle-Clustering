class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    uniq_words.each_with_object({}) do |word,acc|
      acc[word] = normalized_words.count(word)
    end
  end

  private
  def normalized_words
    @words ||= @input.downcase.scan(/\w+/)
  end

  def uniq_words
    @uniq_words ||= normalized_words.uniq
  end
end
