module ResponsePatterns
  def blank?
    match(/\A\s*\z/)
  end

  def shouting?
    match(/\A([A-Z]|\d|\s|\W)+(\!|\?)*\z/)
  end

  def question?
    match(/\A.*\?\z/)
  end
end

class Bob
  def hey(message)
    message.extend(ResponsePatterns)
    return "Fine. Be that way!" if message.blank?
    return "Woah, chill out!"   if message.shouting?
    return "Sure."              if message.question?

    "Whatever."
  end
end
