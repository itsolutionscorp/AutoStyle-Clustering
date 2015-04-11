class Bob

    def hey(str)
        punc = str[-1]

        return 'Fine. Be that way!' if str.strip == ""
        return 'Woah, chill out!' if str == str.upcase && str =~ /[A-Z]/
        return "Sure." if punc == "?"
        return "Whatever."        
    end
end
