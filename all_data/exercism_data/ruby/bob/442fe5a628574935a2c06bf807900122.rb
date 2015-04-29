class Bob

  def hey speech
    RESPONSES.each {|condition, response| return response if self.send(condition, speech)}
  end

  private

  RESPONSES = {silence?: 'Fine. Be that way!',
               shouting?: 'Woah, chill out!',
               question?: 'Sure.',
               other?: 'Whatever.'}

  def silence?(speech)
    speech.nil? or speech.strip.empty?
  end

  def shouting?(speech)
    speech == speech.upcase
  end

  def question?(speech)
    speech.end_with? '?'
  end

  def other?(speech)
    true
  end

end
