class Phrase

  attr_reader :word_count

  def initialize(phrase)
    @word_count ||= {}
    @original = phrase
    grouped_words.each(&count_occurrences)
  end

private

  def words
    @words ||=
      @original
        .downcase
        .split(/\W+/)
  end

  def grouped_words
    @grouped_words ||= words.group_by(&:to_s)
  end

  def count_occurrences
    @counter_lambda ||= -> ((word, occurrences)) {
      word_count[word] = occurrences.size
    }
  end

end
