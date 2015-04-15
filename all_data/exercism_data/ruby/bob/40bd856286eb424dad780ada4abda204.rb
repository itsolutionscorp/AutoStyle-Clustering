class Bob

  attr_accessor :talk

  def hey(talk)

    @talk = talk

    case
      when shouting?
        'Whoa, chill out!'
      when question?
        'Sure.'
      when silent?
        'Fine. Be that way!'
      else
        'Whatever.'
      end
  end

  def shouting?
    @talk == talk.upcase && @talk.match(/[A-z]/) != nil
  end

  def question?
    @talk.end_with?("?")
  end

  def silent?
    @talk.strip.empty?
  end

end
