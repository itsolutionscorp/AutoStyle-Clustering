class Bob
    def hey(phrase)
        phrase.strip!
        response = "Whatever."
        response = "Sure." if phrase[-1, 1] == "?"
        response = "Woah, chill out!" if phrase.upcase == phrase
        response = "Fine. Be that way!" if phrase == ""
        response
    end
end
