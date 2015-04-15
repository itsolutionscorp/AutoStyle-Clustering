class Bob
    def hey(inputStr)
    	sentence = Sentence.new(inputStr)
        if sentence.blank?
            'Fine. Be that way!'
        elsif  sentence.exclamation?
            'Woah, chill out!'
        elsif sentence.question?
            'Sure.'
        else
            "Whatever."
        end
    end

    class Sentence
        def initialize(text)
            @text = text
        end
        def blank?
            @text.strip.empty?  
        end
        def exclamation?
            @text =~ /\p{Upper}/ && @text == @text.upcase
        end
        def question?
            @text.end_with?('?')
        end
    end
end
