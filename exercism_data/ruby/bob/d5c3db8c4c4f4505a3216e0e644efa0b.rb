class Bob

  def hey(message)    
    heard = listen(message)
    answer(heard)
  end
  
  private

   @@ANSWERS = {silence: 'Fine. Be that way!', shout: 'Woah, chill out!', question: 'Sure.', other: 'Whatever.'}
   
  def listen(message)
    @message = message
    if silence?
      :silence
    elsif shout?
      :shout
    elsif question?
      :question
    else
      :other
    end        
  end
  
  def answer(heard)
    @@ANSWERS[heard]
  end
  
  def silence?
    @message =~ /\A\s*\Z/
  end
  
  def shout?
    @message.upcase == @message && @message.downcase != @message
  end
  
  def question?
    @message[-1] == '?'
  end
  
end
