class Bob
  def hey(talk)
    SpeechAct.new(talk).response
  end
end

class SpeechAct
  def initialize(talk)
    @talk = talk
  end

  def response
    # cache response but delay initializing until needed
    return @response if @response

    if empty?
      @response = "Fine. Be that way!"
    elsif shout?
      @response = "Woah, chill out!"
    elsif question?
      @response = "Sure."
    else
      @response = "Whatever."
    end
  end



  private
  def empty?
    @talk.to_s.strip.empty?
  end

  private
  def shout?
    @talk == @talk.upcase
  end

  private
  def question?
    @talk.end_with?("?")
  end
end
