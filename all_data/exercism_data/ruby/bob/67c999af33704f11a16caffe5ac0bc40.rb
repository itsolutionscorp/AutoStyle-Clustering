class Bob
  def hey(say)
    @say = say
    @response = "Whatever."
    nil_or_empty?('Fine. Be that way!') ||
    all_caps?('Woah, chill out!') ||
    question?('Sure.')
    @response
  end


  private

  def nil_or_empty?(message)
    if @say.to_s.length == 0
      @response = message
    end
  end

  def all_caps?(message)
    if @say.upcase == @say
      @response = message
    end
  end

  def question?(message)
    if @say[-1] == '?'
      @response = message
    end
  end
end
