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
  def words
    @words ||= @input.split(/\W+/)
  end

  def normalized_words
    @normalized_words ||= words.map(&:downcase)
  end

  def uniq_words
    @uniq_words ||= normalized_words.uniq
  end
end
