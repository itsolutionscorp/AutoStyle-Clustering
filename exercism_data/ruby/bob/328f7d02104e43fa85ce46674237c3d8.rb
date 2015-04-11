class Bob

    def hey(sentence)
        sentence = sentence.strip
        return 'Fine. Be that way!' if sentence.empty?
        return 'Woah, chill out!' if sentence.index(/[[:alpha:]]/) != nil and sentence == sentence.upcase
        return 'Sure.' if sentence.end_with? '?'
        return 'Whatever.'
    end

end
