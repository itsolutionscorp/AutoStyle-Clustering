class Phrase

    def initialize phrase
        @phrase = phrase
    end

    def word_count
        return {} if @phrase.nil?

        counter = Hash.new(0)

        (@phrase).scan(/\w+/).each do |word|
            word.downcase!
            counter[word] = counter[word] + 1
        end

        counter
    end
end
