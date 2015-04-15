class Bob

  def initialize
    @teenage_responces = {
      stating: 'Whatever.',
      questioned: 'Sure.', 
      shouted: 'Woah, chill out!', 
      silence: 'Fine. Be that way!'
      }
  end

  def hey(drivel)
    answer(check_sentence(drivel))
  end   

  def check_sentence(drivel)
    case 
      when drivel.strip.empty? :silence
      when drivel == drivel.upcase :shouted
      when drivel.end_with?("?") :questioned
      else
        :stating
    end
  end

  def answer(teen_speak)
    @teen_speak = @teenage_responces[teen_speak]
  end
end
