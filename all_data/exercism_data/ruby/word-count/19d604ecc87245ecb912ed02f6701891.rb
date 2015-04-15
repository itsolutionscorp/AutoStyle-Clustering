class Phrase

    @@word_limits = /[^a-z0-9']/i

    attr_reader :word_count

    def initialize(phrase)
        words = phrase.split(@@word_limits)
        @word_count = words.each_with_object(Hash.new(0)) do |word, counts|
            counts[word.downcase] += 1 unless word == ""
        end
    end
end
