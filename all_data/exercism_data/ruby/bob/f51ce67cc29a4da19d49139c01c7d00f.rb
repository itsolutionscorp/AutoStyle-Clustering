class Bob
  def hey(phrase)
    if empty?(phrase)
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
      phrase == phrase.upcase and !phrase.match(/[A-Za-z]/).nil?
    end

    def question?(phrase)
      phrase.split('').last == "?"
    end

    def empty?(phrase)
      phrase.match(/\w/).nil? ? true : false
    end
end
