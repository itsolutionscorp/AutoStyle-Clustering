class Phrase

  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word.downcase] += 1
    end
  end

  private

    def words
      @sentence.split(/[^a-zA-Z0-9']+/)
    end

end
