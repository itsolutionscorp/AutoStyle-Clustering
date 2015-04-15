class Bob
  def hey(phrase)
    return "Sure." if question?(phrase)
    return "Whatever." if stating?(phrase)
    return "Woah, chill out!" if shouting?(phrase)
    return "Fine. Be that way." if silence?(phrase)
  end

  private 
    def question?(phrase)
      # Has a question mark at the end.
      phrase =~ /\?$/
    end

    def stating?(phrase)
      # Has a lowercase (means they are not shouting).
      phrase =~ /[a-z]+/
    end

    def shouting?(phrase)
      # No lowercase letters means shouting.
      phrase =~ /[^a-z]+$/
    end

    def silence?(phrase)
      phrase.nil? || phrase.empty?
    end
end
