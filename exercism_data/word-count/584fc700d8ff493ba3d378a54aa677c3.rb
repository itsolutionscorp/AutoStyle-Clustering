class Phrase
    def initialize(source)
        @source = source
    end

    def word_count
        data = Hash.new(0)
        @source.downcase.scan(/\w+/) do |word|
            data[word] += 1
        end
        data
    end
end
