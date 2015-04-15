class Bob
    def new
    end

    def shouting?(str)
        uppercase = str.upcase
        return (str == uppercase) && (uppercase != str.downcase)
    end

    def asking?(str)
        return str[-1,1] == '?'
    end

    def empty?(str)
        return str.strip.length <= 0
    end

    def hey(str)
        return 'Fine. Be that way!' if empty? str
        return 'Woah, chill out!' if shouting? str
        return 'Sure.' if asking? str

        'Whatever.'
    end
end
