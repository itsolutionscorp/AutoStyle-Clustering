require "active_support/all" # Install activesupport to test.

class Bob
    def hey message
        case
        when message.blank? # What message?
            "Fine. Be that way!"
        when message == message.upcase # Message is all caps.
            "Woah, chill out!"
        when message.ends_with?("?") # Message is a question.
            "Sure."
        else
            "Whatever."
        end
    end
end
