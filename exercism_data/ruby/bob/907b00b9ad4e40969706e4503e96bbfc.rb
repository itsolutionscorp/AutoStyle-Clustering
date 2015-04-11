class Bob
    def hey(message)
        return "Fine. Be that way!" if is_empty?(message)
        return "Woah, chill out!" if is_yelling?(message)
        return "Sure." if is_a_question?(message)
        "Whatever."
    end

    private
    def is_empty?(message)
        message.strip.empty?
    end

    def is_yelling?(message)
        message.upcase == message
    end

    def is_a_question?(message)
        message.end_with?("?")
    end
end
