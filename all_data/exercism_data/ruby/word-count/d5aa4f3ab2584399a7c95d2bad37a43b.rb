class Phrase
    def initialize(words)
        @words_hash = Hash.new(0)
        words.gsub(/[!!&@$%^&,:.]/,' ').split(' ').each { |word| @words_hash[word.downcase] += 1 }
    end

    def word_count
        @words_hash
    end
end
