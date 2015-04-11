class String
    def all_upcase?
        self.upcase == self
    end
    def some_upcase?
        self.downcase != self
    end
    def blank?
        self !~ /[^[:space:]]/
    end
end

class Bob

    def hey msg
        return 'Fine. Be that way!' if empty(msg)
        return 'Woah, chill out!' if shouting(msg)
        return 'Sure.' if question(msg)
        'Whatever.'
    end

    def empty msg
        msg.blank?
    end

    def shouting msg
        msg.all_upcase? and msg.some_upcase?
    end

    def question msg
        msg[-1] == '?'
    end

end
