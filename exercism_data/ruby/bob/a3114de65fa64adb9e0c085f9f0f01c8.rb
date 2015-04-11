class Bob
  def hey(ask)
    @ask = ask.strip

    if nothing?
      'Fine. Be that way!'
    elsif yell?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
    def nothing?
      @ask.empty?
    end

    def yell?
      @ask.upcase == @ask
    end

    def question?
      @ask.end_with? '?'
    end
end
