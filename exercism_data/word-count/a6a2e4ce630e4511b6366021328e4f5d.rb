class Phrase
  def initialize string
    @string = string
  end

  def word_count
    build_word_count_hash
  end

  private
  attr_reader :string
  attr_accessor :words

  def words
    string.downcase
      .split(/[^0-9a-z]/i)
      .reject &:empty?
  end

  def build_word_count_hash
    words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end
end
