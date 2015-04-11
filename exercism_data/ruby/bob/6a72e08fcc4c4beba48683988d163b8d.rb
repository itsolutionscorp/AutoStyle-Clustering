class Speech
  attr_reader :source

  def initialize(source)
    @source = source
  end

  def silence?
    source.nil? or source.strip.empty?
  end

  def shouting?
    source == source.upcase
  end

  def question?
    source.end_with? '?'
  end

  def type
    if silence?
      :silence
    elsif shouting?
      :shouting
    elsif question?
      :question
    else
      :other
    end
  end

end

class Bob

  def hey speech
    RESPONSES[Speech.new(speech).type]
  end

  private

  RESPONSES = {silence: 'Fine. Be that way!',
               shouting: 'Woah, chill out!',
               question: 'Sure.',
               other: 'Whatever.'}

end
