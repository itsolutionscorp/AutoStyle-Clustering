class Bob
    def hey message
        if empty? message
            return "Fine. Be that way!"
        elsif yelling? message
            return "Woah, chill out!"
        elsif question? message
            return "Sure."
        else
            return "Whatever."
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
