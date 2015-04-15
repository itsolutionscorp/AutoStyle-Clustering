class Bob
  def hey(phrase)
    return "Fine. Be that way!" if not_saying_anything?(phrase)
    return "Woah, chill out!"   if yelling?(phrase)
    return "Sure."              if question?(phrase)
    return "Whatever."
  end

  private
    def not_saying_anything?(phrase)
      phrase.nil? or phrase.empty?
    end

    def yelling?(phrase)
      phrase.upcase == phrase
    end

    def question?(phrase)
      phrase.end_with? '?'
    end
end
