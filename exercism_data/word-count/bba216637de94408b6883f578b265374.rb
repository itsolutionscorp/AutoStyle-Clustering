class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    Hash.new(0).tap do |hash|
      words.map { |word| hash[word] += 1 }
    end
  end

  private
  attr_reader :phrase

  def words
    delimited_strings.delete_if(&:empty?)
  end

  def delimited_strings
    lowercase.split(/\W/)
  end

  def lowercase
    phrase.downcase
  end
end
