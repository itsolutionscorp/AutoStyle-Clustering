class Phrase
  attr_reader :word_count

  def initialize(words)
      words_array = find_all_words(words)
      count_each_word(words_array)
    end

  private
    def find_all_words(words)
      words.downcase.scan(/\w+/)
    end

    def count_each_word(words_array) 
      @word_count = Hash.new(0)
      words_array.each do |word|
      	@word_count[word] += 1
      end
    end
end
