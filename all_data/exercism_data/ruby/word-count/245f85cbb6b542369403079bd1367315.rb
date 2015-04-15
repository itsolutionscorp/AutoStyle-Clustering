
class Phrase
    def initialize(phrase)
        @count = Hash.new
        words = phrase.split(/[ |,]/)
        words.each do |word|
            word = normalise(word)
            next if word == ''
            if @count[word] == nil
                @count[word] = 1
            else
                @count[word] += 1
            end
        end
    end
    
    def word_count
        @count
    end
    
    private
    def normalise(word)
        word = word.downcase
        word.gsub!(/[\:\.\!\&\@\$\%\^]/, '')
        word
    end
end
