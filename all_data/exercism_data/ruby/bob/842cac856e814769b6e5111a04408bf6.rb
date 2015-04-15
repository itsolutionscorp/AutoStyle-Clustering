class String
    def question?
        self.end_with? "?"
    end
    def yelling?
        #self.end_with? "!" or self.all_upper?
        self.all_upper?
    end
    def all_upper?
        self =~ /[[:upper:]]/ and not self =~ /[[:lower:]]/
    end
end

class Bob
    def hey(_str)
        str = _str.strip
        if str.yelling?
            "Woah, chill out!"
        elsif str.question?
            "Sure."
        elsif str.empty?
            "Fine. Be that way!"
        else
            "Whatever."
        end
    end
end
