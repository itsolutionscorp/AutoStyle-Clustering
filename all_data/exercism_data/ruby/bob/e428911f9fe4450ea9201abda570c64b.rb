class Phrase

  def initialize(phrase)
    @phrase = phrase
    remove_punctuation!
    downcase_words!
  end

  def word_count
    Hash[unique_words.zip word_counts]
  end

  private

  def unique_words
    @phrase.split(' ').uniq
  end

  def words
    @phrase.split(' ')
  end

  def word_counts
    unique_words.map{|uw| words.count{|w| uw == w}}
  end

  def remove_punctuation!
    @phrase = @phrase.gsub(/(\W)/," ")
  end

  def downcase_words!
    @phrase = words.map{|x| x.downcase}.join(' ')
  end

end
