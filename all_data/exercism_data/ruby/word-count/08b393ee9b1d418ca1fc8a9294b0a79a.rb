class Phrase
  attr_reader :string
  def initialize(string)
    @string = string
  end

  def word_count
    hash = Hash.new { |h, k| h[k] = 0 }
    words.each_with_object(hash) do |word, hash|
      hash[word] += 1
    end
  end

  private

  def words
    string.downcase.scan(/\w+/)
  end
end
