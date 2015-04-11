class Bob
  def hey(text)
    return "Fine. Be that way." if nothing_said?(text)
    response = "Whatever."
    response = "Woah, chill out!" if yelling?(text)
    response = "Sure." if question?(text)
    response
  end

  private
    def yelling?(text)
      text.upcase == text
    end

    def question?(text)
      text.end_with?('?')
    end

    def nothing_said?(text)
      text.nil? || (text && text.empty?)
    end
end
