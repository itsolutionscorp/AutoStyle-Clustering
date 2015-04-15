class Bob
    def hey(phrase)
        return 'Fine. Be that way!' if phrase.strip == ''
        return 'Whoa, chill out!' if phrase == phrase.upcase && phrase.match(/[A-Z]/)
        return 'Sure.' if phrase.end_with? '?'
        return 'Whatever.'
    end
end
