class Bob
    def hey(x)
        case
        when (x =~ /[A-Z]/ and x !~ /[a-z]/)
            'Woah, chill out!'
        when x =~ /\?\z/m
            'Sure.'
        when x =~ /\A *\z/
            'Fine. Be that way!'
        else
            "Whatever."
        end
    end
end
