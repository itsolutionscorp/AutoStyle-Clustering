class Bob
  RESPONSES = { question: 'Sure.',
                yelling: 'Woah, chill out!',
                empty: 'Fine. Be that way.',
                present: 'Whatever.' }

  def hey(conversation)
    respond_to conversation
  end

  def respond_to(conversation)
    RESPONSES[conversation_type(conversation.to_s)]
  end

  def conversation_type(conversation)
    if conversation.empty?
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
end
