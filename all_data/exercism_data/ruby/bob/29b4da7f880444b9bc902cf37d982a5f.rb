class Bob

  def hey(text)
    return "Fine. Be that way!" if silence?(text)
    return "Woah, chill out!" if all_caps?(text)
    return "Sure." if question?(text)
    "Whatever."
  end


  private
    def all_caps?(text)
      text.index(/[A-Z]/) && text == text.upcase
    end

    def question?(text)
      text[-1] == '?'
    end

    def silence?(text)
      text =~ /\A\s*\Z/
    end

end
