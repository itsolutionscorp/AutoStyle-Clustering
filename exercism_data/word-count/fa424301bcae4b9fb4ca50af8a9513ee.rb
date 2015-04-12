class Phrase

  def initialize(phrase)
    @original = phrase
  end

  def word_count
    @word_count ||=
      grouped_words.each_with_object({}) do |(word, occurrences), counts|
        counts[word] = occurrences.size
      end
  end

private

  def words
    @words ||=
      @original
        .downcase
        .scan(/\w+/)
  end

  def grouped_words
    @grouped_words ||= words.group_by(&:to_s)
  end

end
