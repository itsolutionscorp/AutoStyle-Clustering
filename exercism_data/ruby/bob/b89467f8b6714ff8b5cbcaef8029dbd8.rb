class Bob
    def shouting?(str)
        uppercase = str.upcase
        (str == uppercase) && (uppercase != str.downcase)
    end

    def silent?(str)
        str.strip.empty?
    end

    def hey(str)
        return 'Fine. Be that way!' if silent? str
        return 'Woah, chill out!' if shouting? str
        return 'Sure.' if str.end_with? '?'
        'Whatever.'
    end
end
