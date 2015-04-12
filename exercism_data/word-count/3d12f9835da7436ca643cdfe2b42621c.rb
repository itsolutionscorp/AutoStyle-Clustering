class Phrase
  def initialize(string)
    @string = string
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word.downcase] += 1
    end
  end

  private
    def words
      @string.scan(/[[:alnum:]]+/)
    end
end
