class Phrase
  attr_reader :words

  def initialize(words)
    @words = words
  end

  def word_count
    all_words = find_all_words(words)
    count_each_word(all_words)
  end

  private
    def find_all_words(words)
      words.downcase.scan(/\w+/)
    end

    def count_each_word(words_array) 
      words_array.each_with_object(Hash.new(0)) {|word, hash| hash[word] += 1 }
    end
end
