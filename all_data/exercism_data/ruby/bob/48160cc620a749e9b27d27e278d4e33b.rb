class Bob
    def hey(sentence)
        if shouting?(sentence)
            'Woah, chill out!'
        elsif question?(sentence)
            'Sure.'
        elsif nothing?(sentence)
            'Fine. Be that way!'
        else
            'Whatever.'
        end
    end

    private

    def shouting?(sentence)
        /[a-zA-Z]/ =~ sentence && sentence.upcase == sentence
    end

    def question?(sentence)
        sentence[-1] == '?'
    end

    def nothing?(sentence)
        sentence.split("\n").join("").match(/^\s*$/)
    end
end
