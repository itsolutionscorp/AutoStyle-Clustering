class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    result = Hash.new(0)
    words.each { |word| result[word] += 1 }
    result
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
