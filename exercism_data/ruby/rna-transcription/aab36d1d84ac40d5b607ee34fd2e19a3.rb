class Bob
  def hey(text)
    return "Fine. Be that way." if silence?(text)
    response = "Whatever."
    response = "Woah, chill out!" if yelling?(text)
    response = "Sure." if asking?(text)
    response
  end

  private
    def yelling?(text)
      text.upcase == text
    end

    def asking?(text)
      text.end_with?('?')
    end

    def silence?(text)
      text.nil? || (text && text.empty?)
    end
end
