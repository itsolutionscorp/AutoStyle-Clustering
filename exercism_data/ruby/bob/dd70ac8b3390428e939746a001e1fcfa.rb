class Bob
  RESPONSES = { question: 'Sure.',
                yelling: 'Woah, chill out!',
                empty: 'Fine. Be that way.',
                present: 'Whatever.' }

  def hey(conversation)
    RESPONSES[conversation_type(conversation.to_s)]
  end

  def conversation_type(conversation)
    if conversation.empty?
      :empty
    elsif is_yelling?(conversation)
      :yelling
    elsif is_question?(conversation)
      :question
    else
      :present
    end
  end

  def is_yelling?(conversation)
    conversation.upcase == conversation unless conversation.empty?
  end

  def is_question?(conversation)
    conversation[-1,1] == '?'
  end
end
