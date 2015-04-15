class Phrase
  attr_reader :phrase

  PUNCTUATION = /[$,.:!?@#%^&*()\[\]{}\"]/

  def initialize(phrase)
    @phrase = phrase.downcase.gsub(PUNCTUATION, ' ')
  end

  def word_count
    {}.tap do |counts|
      words.each do |word|
        counts[word] = split_words.count(word)
      end
    end
  end

  private

  def words
    @words ||= split_words.uniq
  end

  def split_words
    @split_words ||= @phrase.split
  end
end
