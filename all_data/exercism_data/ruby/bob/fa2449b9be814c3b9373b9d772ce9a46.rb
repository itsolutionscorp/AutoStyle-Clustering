class Bob

  def hey(saying)
    @say = saying
    if shouting
      return 'Woah, chill out!'
    elsif ask_question
      return 'Sure.'
    elsif silent_treatment
      return 'Fine. Be that way!'
    else
      return 'Whatever.'
    end
  end

  private

    def shouting
      @say == @say.upcase && /[a-zA-Z]+/ =~ @say
    end

    def ask_question
      @say[-1] == '?'
    end

    def silent_treatment
      @say.strip.empty?
    end

end
