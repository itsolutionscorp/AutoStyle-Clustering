class Phrase
  attr_reader :word_count

  def initialize(words)
    count(words)
  end

  private
    def count(words)
      words_array = find_all_words(words)
      count_each_word(words_array)
    end

    def find_all_words(words)
      words.downcase.scan(/\w+/)
    end

    def count_each_word(words_array) 
      @word_count = {}
      words_array.each do |word|
        if @word_count.has_key?(word)
          @word_count[word] += 1
        else
          @word_count[word] = 1
        end
      end
    end
end
