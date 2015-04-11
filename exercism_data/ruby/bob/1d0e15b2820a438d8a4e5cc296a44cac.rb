class Bob
  def hey(phrase)
    if yell?(phrase)
      "Woah, chill out!"
    elsif question?(phrase)
      "Sure."
    elsif without_anything?(phrase)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private
    def question?(phrase)
      phrase[-1] == '?'
    end

    def yell?(phrase)
      upcases_phrase = phrase.upcase
      (upcases_phrase.match(/[A-Z]/)) && (phrase == upcases_phrase)
    end

    def without_anything?(phrase)
      phrase.strip.empty?
    end
end
