class Bob

  def initialize
    @conditions = {silence?: 'Fine. Be that way!',
                   shouting?: 'Woah, chill out!',
                   question?: 'Sure.'}
  end

  def hey speech
    @conditions.each {|condition, response| return response if self.send(condition, speech)}
    'Whatever.'
  end

  def silence?(speech)
    speech.nil? or speech.strip.empty?
  end

  def shouting?(speech)
    speech == speech.upcase
  end

  def question?(speech)
    speech.end_with? '?'
  end

end
