class Bob
    def hey(inputStr)
    	sentence = Sentence.new(inputStr)
        return 'Fine. Be that way!' if sentence.blank?
        return 'Woah, chill out!' if sentence.exclamation?
        return 'Sure.' if sentence.question?
        return "Whatever."
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
