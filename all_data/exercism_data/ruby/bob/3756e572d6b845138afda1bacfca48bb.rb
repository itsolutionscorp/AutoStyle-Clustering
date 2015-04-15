class Bob
  RESPONSES = { question: 'Sure.',
                yelling: 'Woah, chill out!',
                empty: 'Fine. Be that way.',
                present: 'Whatever.' }

  def respond_to(conversation)
    conversation = conversation.to_s

    if silence?(conversation)
      RESPONSES[:empty]
    elsif yelling?(conversation)
      RESPONSES[:yelling]
    elsif question?(conversation)
      RESPONSES[:question]
    else
      RESPONSES[:present]
    end
  end
  alias_method :hey, :respond_to

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
