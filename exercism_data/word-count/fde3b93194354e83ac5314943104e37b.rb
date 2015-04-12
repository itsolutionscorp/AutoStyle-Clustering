class Phrase
  def initialize(text)
    @text = text.to_s
  end

  def ignoring_case
    original_text = @text.dup
    @text.downcase!

    result = yield

    @text = original_text

    result
  end

  def split_words
    @text.scan(/\w+/)
  end

  def word_count
    ignoring_case do
      @words = split_words

      @words.each_with_object(Hash.new(0)) do |word, counts|
        counts[word] += 1
      end
    end
  end
end
