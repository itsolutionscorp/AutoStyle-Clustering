class Bob
  def hey(phrase)
    if silent?(phrase)
      "Fine. Be that way!"
    elsif yelling?(phrase)
      "Whoa, chill out!"
    elsif question?(phrase)
      "Sure."
    else
      "Whatever."
    end
  end

  private
    def yelling?(phrase)
      phrase == phrase.upcase and phrase =~ /[A-Z]/
    end

    def question?(phrase)
      phrase.end_with?("?")
    end

    def silent?(phrase)
      phrase.strip.empty?
    end
end
