class Bob

    def hey message
        return 'Fine. Be that way!' if message.strip.empty?
        return 'Woah, chill out!' if message.upcase == message && message.downcase != message
        return 'Sure.' if message.end_with? '?'
        return 'Whatever.'
    end

end
