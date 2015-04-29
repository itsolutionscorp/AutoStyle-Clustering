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
        /[A-Z]/ =~ sentence && /[a-z]/ !~ sentence
    end

    def question?(sentence)
        sentence[-1] == '?'
    end

    def nothing?(sentence)
        sentence.split("\n").join("").match(/^\s*$/)
    end
end
