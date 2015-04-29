class Bob

    def hey(sentence)
        sentence = sentence.strip
        return 'Fine. Be that way!' if silence? sentence
        return 'Woah, chill out!' if shouting? sentence
        return 'Sure.' if asking? sentence
        return 'Whatever.'
    end

    def silence?(sentence)
    	sentence.empty?
    end

    def shouting?(sentence)
    	sentence.index(/[[:alpha:]]/) != nil and sentence == sentence.upcase
    end

    def asking?(sentence)
    	sentence.end_with? '?'
    end

end
