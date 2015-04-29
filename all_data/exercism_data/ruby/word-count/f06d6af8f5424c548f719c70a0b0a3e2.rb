class Phrase

  def initialize(string)
    @string = string
  end

  def word_count
    words_normalised.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

  private

  def words_normalised
    @string.downcase.scan(/['\w]+/)
  end

end
