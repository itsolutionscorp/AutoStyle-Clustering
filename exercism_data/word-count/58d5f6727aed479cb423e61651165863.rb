class Phrase
  def initialize input
    @input = input
  end

  def word_count
    Hash[word_and_count_pairs]
  end

  private

  def words
    @input.gsub(/[^A-Za-z0-9 ]/, " ").downcase.split
  end

  def word_and_count_pairs
    words.uniq.map do |word|
      word_and_count word
    end
  end

  def word_and_count word
    [word, words.count(word)]
  end
end
