class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    Hash[*words.uniq.map do |word|
      [word, words.count(word)]
    end.flatten]
  end

  private

  def words
    @phrase.split(/[\s,.]/).map do |potential_word|
      if potential_word.match(/[\w']+/)
        potential_word.match(/[\w']+/)[0].downcase
      end
    end.reject(&:nil?)
  end
end
