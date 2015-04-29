class Phrase
    def initialize word
        @word = word
    end
    def word_count
        words = @word.split(' ')

        Hash[words.group_by { |w| w }.map { |w, ws| [w, ws.length] }]
    end
end
