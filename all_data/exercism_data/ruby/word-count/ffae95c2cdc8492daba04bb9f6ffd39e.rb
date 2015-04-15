class Phrase
    def initialize text
        @text = text.downcase
    end

    def word_count
        word_count = {}
        text_to_array.each do |word|
            word_count[word] = text_to_array.count word unless word_count.include? word
        end

        return word_count
    end

    def text_to_array
        return @text.split(/\W+/)
    end
end
