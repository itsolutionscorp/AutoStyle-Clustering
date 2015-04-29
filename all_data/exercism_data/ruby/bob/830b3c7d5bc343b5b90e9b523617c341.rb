class Bob

    def hey message
        if message.nil? || message.empty?
            "Fine. Be that way!"
        elsif yell?(message)
            "Woah, chill out!"
        elsif question?(message)
            "Sure."
        else
            "Whatever."
        end
    end

    private

        def yell? message
            message.upcase == message
        end

        def question? message
            message.end_with? "?"
        end

end
