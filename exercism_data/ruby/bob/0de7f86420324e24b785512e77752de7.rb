class Bob

  WHATEVER          = 'Whatever.'
  SHOUTING          = 'Woah, chill out!'
  SURE              = 'Sure.'
  FINE              = 'Fine. Be that way!'

  attr_reader :line

  def hey(line)
    @line = line
    return FINE              if silence?
    return SHOUTING          if upcase?
    return SURE              if asking_a_question?
    return WHATEVER
  end

  private

    def silence?
      @line.strip == ''
    end

    def asking_a_question?
      @line.end_with?('?')
    end

    def upcase?
      @line.upcase == @line && @line.downcase != @line
    end

end
