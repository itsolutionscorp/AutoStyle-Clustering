class Phrase
  def initialize(sentence)
    @word_hash = word_array_to_hash(sentence
                                    .downcase
                                    .split(/\W+/))
  end

  def word_count
    @word_hash
  end

  private
    def word_array_to_hash(word_array)
      word_hash = Hash.new(0)
      word_array.each do |word|
        word_hash[word] += 1
      end
      word_hash
    end
end
