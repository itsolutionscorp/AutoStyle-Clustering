class Bob 


  def hey(phrase="")
    return 'Fine. Be that way!' if said_nothing?(phrase)
    return 'Woah, chill out!' if shouting?(phrase)
    return 'Sure.' if ask_question?(phrase)
    
    'Whatever.'
  end

  private
    def said_nothing?(phrase)
      phrase.strip.empty?
    end

    def shouting?(phrase)
      phrase == phrase.upcase
    end

    def ask_question?(phrase)
      phrase.end_with?("?")
    end
  

end

# bob = Bob.new
# puts bob.hey()
