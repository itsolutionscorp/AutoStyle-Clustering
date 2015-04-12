class Phrase

    @@word_pattern = /[^a-z0-9']/i

    attr_reader :word_count

    def initialize(phrase)
        @word_count = phrase.split(@@word_pattern).each_with_object(Hash.new(0)) do |word, counts|
            counts[word.downcase] += 1 unless word == ""
        end
    end
end
