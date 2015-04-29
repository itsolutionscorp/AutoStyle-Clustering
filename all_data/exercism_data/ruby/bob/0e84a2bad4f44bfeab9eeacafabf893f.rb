class Bob
  @@known_responses = {
    :silence => "Fine. Be that way!",
    :shouting => "Woah, chill out!",
    :question => "Sure.",
    :generic => "Whatever."
  }

  def hey ( input )
    respond_to_sentiment ( parse_sentiment ( input ) )
  end

  def parse_sentiment ( input )
    if input.strip.empty?
      :silence
    elsif input == input.upcase and /[a-zA-Z]/ =~ input
      :shouting
    elsif input.end_with?("?")
      :question
    else
      :unknown
    end
  end

  def respond_to_sentiment ( sentiment )
    @@known_responses[sentiment] || @@known_responses[:generic]
  end
end
