class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    map = words.group_by { |word| word }.map do |word, ocurrences|
      [word, ocurrences.size]
    end

    Hash[map]
  end

  private

  def words
    @phrase.downcase.split(/\W+/)
  end
end
