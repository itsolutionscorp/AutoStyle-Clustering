class Phrase
  attr_reader :word_count

  def initialize(words)
    all_words = find_all_words(words)
    @word_count = count_each_word(all_words)
  end

  private
    def find_all_words(words)
      words.downcase.scan(/\w+/)
    end

    def count_each_word(words_array) 
      words_array.each_with_object(Hash.new(0)) {|word, hash| hash[word] += 1 }
    end
end
