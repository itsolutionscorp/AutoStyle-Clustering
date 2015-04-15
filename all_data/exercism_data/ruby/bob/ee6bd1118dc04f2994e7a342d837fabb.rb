class Bob

  def hey(request)
    return "Fine. Be that way!" if silence?(request)
    return "Woah, chill out!" if is_yelling?(request)
    return "Sure." if is_question?(request)
    "Whatever."
  end

  private

  def silence?(request)
    request.strip.empty?
  end

  def is_yelling?(request)
    request.eql?(request.upcase) && request.match(/[A-Z]+/)
  end

  def is_question?(request)
    request.end_with?("?")
  end

end
