class Bob
  attr_reader :sentence

  def hey sentence
    @sentence = sentence
    if silent
      'Fine. Be that way!' 
    elsif  shouting
       'Woah, chill out!' 
    elsif question
      "Sure."
    else 
      'Whatever.'
    end
  end

  def silent 
     sentence.nil? || sentence.empty?
  end

  def shouting 
    sentence == sentence.upcase    
  end

  def question 
    sentence.end_with?('?') 
  end

end



kid = Bob.new
puts kid.hey('Tom-ay-to, tom-aaaah-to.')                                              # Whatever
puts kid.hey('WATCH OUT!')                                                                      # Woah, chill out!
puts kid.hey('Does this cryogenic chamber make me look fat?')           # Sure.
puts kid.hey("Let's go make out behind the gym!")                                # Whatever
puts kid.hey("It's OK if you don't want to go to the DMV.")                    # Whatever
puts kid.hey('WHAT THE HELL WERE YOU THINKING?')                           # Woah, chill out!
puts kid.hey('1, 2, 3 GO!')                                                                          # Woah, chill out!
puts kid.hey('ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!')   # Woah, chill out!
puts kid.hey('I HATE YOU')                                                                        # Woah, chill out!
puts kid.hey('Ending with ? means a question.')                                     # Whatever
