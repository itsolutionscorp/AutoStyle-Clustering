class Bob
    def hey(x)
        case
        when (x != x.downcase and x == x.upcase)
            'Woah, chill out!'
        when x.end_with?("?")
            'Sure.'
        when (x.strip == "")
            'Fine. Be that way!'
        else
            "Whatever."
        end
    end
end
