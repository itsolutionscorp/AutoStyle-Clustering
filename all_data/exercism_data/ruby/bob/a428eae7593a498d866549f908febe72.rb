class Bob
  RESPONSES = { question: 'Sure.',
                yelling: 'Woah, chill out!',
                empty: 'Fine. Be that way.',
                present: 'Whatever.' }

  def respond_to(conversation)
    RESPONSES[conversation_type(conversation.to_s)]
  end
  alias_method :hey, :respond_to

  def conversation_type(conversation)
    if silence?(conversation)
      :empty
    elsif yelling?(conversation)
      :yelling
    elsif question?(conversation)
      :question
    else
      :present
    end
  end

  def yelling?(conversation)
    conversation.upcase == conversation
  end

  def question?(conversation)
    conversation.end_with?('?')
  end

  def silence?(conversation)
    conversation.empty?
  end
end
