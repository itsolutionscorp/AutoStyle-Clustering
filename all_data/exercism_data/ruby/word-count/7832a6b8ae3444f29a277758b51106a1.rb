class Phrase

  def initialize(string)
    @string = string
  end

  def word_count
    count = Hash.new 0
    words_normalised.each do |word|
      count[word] += 1
    end
    count
  end

  private

  def words_normalised
    @string.downcase.scan(/['\w]+/)
  end

end
