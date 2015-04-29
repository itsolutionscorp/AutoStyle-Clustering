class Bob

  def initialize
    @TEENAGE_RESPONCES = {stating: 'Whatever.',
                         questioned: 'Sure.', 
                         shouted: 'Woah, chill out!',
                         silence: 'Fine. Be that way!'
                        }
  end

  def hey(drivel)
    answer(check_sentence(drivel)) #@teen_speak or @TEENAGE_RESPONCE[:stating].to_s 
  end   

  def check_sentence(drivel)
    if drivel.empty? or drivel.match /^\s*$/ 
      @TEENAGE_RESPONCES[:silence] unless drivel.include?("\n")
    elsif drivel == drivel.upcase
      @TEENAGE_RESPONCES[:shouted]
    elsif drivel.end_with?("?")
      @TEENAGE_RESPONCES[:questioned]
    end
  end

  def answer(*teen_speak)
    @TEENAGE_RESPONCE[:stating] unless teen_speak.first
  end
end
  
