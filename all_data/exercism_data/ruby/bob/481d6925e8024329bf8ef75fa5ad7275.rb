class Bob
  def hey(message)
    @prompt = message
    return response
  end

  private
  def response
    return "Fine. Be that way!" if silence?
    return "Woah, chill out!"   if shouting?
    return "Sure."              if question?
    return "Whatever."        # if anything else
  end

  def silence?      # Entire @prompt is 0 or more whitespace characters
    return @prompt =~ /\A\s*\Z/
  end

  def question?     # Last character of @prompt is '?'
    return @prompt =~ /\?\Z/
  end

  def shouting?     # Entire @prompt is 0 or more chars which are not lowercase
    return @prompt =~ /\A[^a-z]*\Z/
  end
end
