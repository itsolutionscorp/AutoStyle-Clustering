class Bob
    def hey(mssg)
        if (shouting? mssg)
            'Woah, chill out!'
        elsif (question? mssg)
            'Sure.'
        elsif (silence? mssg)
            'Fine. Be that way!'
        else
            'Whatever.'
        end
    end

private
    def shouting? mssg
        any_upper = nil != mssg[/[A-Z]/]
        any_lower = nil != mssg[/[a-z]/]
        any_upper && !any_lower
    end

    def question? mssg
        mssg.end_with? "?"
    end

    def silence? mssg
        mssg.strip.empty?
    end
end
