class Phrase

    def initialize phrase
        @phrase = phrase
    end

    def word_count
        count_words
    end

    private

    def count_words
    	@word_count = {}
        split_phrase.collect do |word| 
            store_word filter_word(word.downcase)
        end
        @word_count
    end

    def split_phrase
    	@phrase.split(%r{,|\s})
    end

    def filter_word word
    	word = word.gsub(/[^0-9A-Za-z\']/, '')
    end

    def store_word word
    	@word_count.store(word, number_of_words(word)) unless word.empty?
    end

    def number_of_words word
        @word_count[word].nil? ? 1 : @word_count[word]+1
    end

end
