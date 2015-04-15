class Phrase
  def initialize sentence
    @sentence = sentence.downcase
  end

  def word_count
    Hash[word_counts]
  end

  private

  def word_counts
    @word_counts ||= word_groups.map do |word, word_occurrences|
      [word, word_occurrences.length]
    end
  end

  def word_groups
    @word_groups ||= words.group_by { |word| word }
  end

  def words
    @words ||= @sentence.scan(/\w+/).lazy
  end

end
