class Bob
    def hey message
        case
        when message.strip == "" # What message?
            "Fine. Be that way!"
        when message == message.upcase # Message is all caps.
            "Woah, chill out!"
        when message =~ /\?$/ # Message is a question.
            "Sure."
        else
            "Whatever."
        end
    end
end
