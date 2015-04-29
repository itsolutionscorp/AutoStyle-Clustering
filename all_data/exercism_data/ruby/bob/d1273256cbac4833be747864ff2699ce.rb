class Bob
  def hey(phrase)
    if not_saying_anything?(phrase)
      "Fine. Be that way!"
    elsif yelling?(phrase)
      'Woah, chill out!'
    elsif question?(phrase)
      'Sure.'
    else
      "Whatever."
    end
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
