class Phrase
  def initialize(string)
    @string = string.downcase
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  private
    def words
      @string.scan(/\p{Alnum}+/)
    end
end
