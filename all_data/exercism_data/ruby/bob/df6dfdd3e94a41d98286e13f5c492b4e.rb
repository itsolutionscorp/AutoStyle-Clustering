class Bob
  def initialize
    @responses = [
      [/\A\s*\z/,"Fine. Be that way!"],
      [/\A[A-Z\d\W]+\z/,"Woah, chill out!"],
      [/\?\z/, "Sure."]
    ]
    @default_response = "Whatever."
  end

  # Respond to a message.
  #
  # message - A string.
  #
  # Returns a string response.
  def hey(message)
    if response = @responses.detect { |regex, _| regex =~ message }
      response.last
    else
      @default_response
    end
  end
end
