class Bob

    def hey(words)
        return "Fine. Be that way!" if words.strip.empty? 
        return "Woah, chill out!" if words == words.upcase && words =~ /[A-Z]/  
        return "Sure." if words =~ /\?\z/ 
        
        "Whatever." 
    end

end
