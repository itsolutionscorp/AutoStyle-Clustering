class Bob

  # Ask a teenager things
  #
  # @return [String] Some witless reply 
  # @example Get typical response
  #   Bob.hey('')
  #   "Whatever."
  def hey(message)
    # return early if they're being rude
    return "Fine. Be that way!" if message.strip.empty?

    if message == message.upcase        # are they yelling at me
      'Woah, chill out!'
    elsif message.end_with?('?')  # are they asking me a question
      "Sure."
    else                                # anything else 
      "Whatever."
    end

  end
end
