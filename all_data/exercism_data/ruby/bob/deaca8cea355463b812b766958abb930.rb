class Bob
    def hey(msg)
        # Shouting has precedence over questions
        if shouting?(msg)
            response = "Woah, chill out!"
        elsif question?(msg)
            response = "Sure."
        elsif passive_aggression?(msg)
            response = "Fine. Be that way!"
        else
            response = "Whatever."
        end
        
        return response
    end
    
    # If has multiple capital letters in > 50% of the the words (heuristic to avoid Acronyms)
    def shouting?(msg)
        matches = msg.scan(/([A-Z]{2,})/)
        num_matches = matches.length
        # number words
        words = msg.scan(/([a-z]|[0.9])+/)
        num_words = words.length
        if (num_matches == 0 )
            return false
        end
        
        if (num_words / num_matches) < 2
            return true
        else
            return false
        end
    end
    
    
    # If string ends on at least one alphanumeric character followed by a question mark
    def question?(msg)
        if msg.index(/([a-z]|[A-Z]|[0-9])+?\?\Z/)
            return true
        else
            return false
        end
        
    end
    
    # If empty
    def passive_aggression?(msg)
        if not msg.index(/[a-z]|[A-Z]|[0-9]/)
            return true
        else
            return false
        end
    end
end
