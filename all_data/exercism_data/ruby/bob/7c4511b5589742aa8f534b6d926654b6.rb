class Bob
  def hey(phrase)
    @phrase = phrase
    if silent?
      "Fine. Be that way!"
    elsif yelling?
      "Whoa, chill out!"
    elsif question?
      "Sure."
    else
      "Whatever."
    end
  end

  private
    def yelling?
      @phrase == @phrase.upcase and @phrase =~ /[A-Z]/
    end

    def question?
      @phrase.end_with?("?")
    end

    def silent?
      @phrase.strip.empty?
    end
end
