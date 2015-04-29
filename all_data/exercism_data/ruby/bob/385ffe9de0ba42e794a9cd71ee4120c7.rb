class Bob

  def hey(message)
    case
    when blank?(message)
      "Fine. Be that way!"
    when shouting?(message)
      "Woah, chill out!"
    when question?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  private

    def blank?(message)
      /\A\s*\z/ === message
    end

    def shouting?(message)
      /\A[A-Z\W\d]+\z/ === message && /[A-Z]/ === message
    end

    def question?(message)
      /\?\z/ === message
    end

end
