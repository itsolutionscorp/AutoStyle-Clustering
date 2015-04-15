class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count = words.group_by { |word| word.downcase }.map { |word, words| [word, words.length] }
    Hash[count]
  end

private

  def words
    @phrase.scan(/\w+/)
  end
end
