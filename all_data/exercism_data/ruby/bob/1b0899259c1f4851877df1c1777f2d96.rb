class Bob
    def hey(inputStr)
    	sentence = Sentence.new(inputStr)

        if sentence.isEmptyOrOnlyWhiteSpaces?
            'Fine. Be that way!'
        elsif  sentence.containsLettersThatAreOnlyCapital?
            'Woah, chill out!'
        elsif sentence.isQuestion?
            'Sure.'
        else
            "Whatever."
        end
    end

    class Sentence < String
        def isEmptyOrOnlyWhiteSpaces?
            strip.empty?  
        end
        def containsLettersThatAreOnlyCapital?
            self =~ /\p{Upper}/ && self == self.upcase
        end
        def isQuestion?
            end_with?('?')
        end
    end
end
