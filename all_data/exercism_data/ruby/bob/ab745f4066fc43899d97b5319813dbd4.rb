class Bob
    def hey(remark)
        if remark.strip.length == 0
            'Fine. Be that way!'
        elsif remark.upcase == remark && remark =~ /[A-Z]/
            'Woah, chill out!'
        elsif remark.end_with? '?'
            'Sure.'
        else
            'Whatever.'
        end
    end
end
