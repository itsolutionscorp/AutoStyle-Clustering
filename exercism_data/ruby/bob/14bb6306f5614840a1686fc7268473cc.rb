class Bob
    def hey message
        case
        when (empty? message)       then "Fine. Be that way!"
        when (yelling? message)     then "Woah, chill out!"
        when (question? message)    then "Sure."
        else                             "Whatever."
        end
    end

    private

    def empty? message
        message.strip.empty?
    end

    def yelling? message
        message.upcase == message
    end

    def question? message
        message.end_with? '?'
    end
end
