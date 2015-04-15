class Bob
  RESPONSES = {
    :forceful => 'Whoa, chill out!',
    :question => 'Sure.',
    :address  => 'Fine. Be that way!',
    :default  => 'Whatever.'
  }

  def hey(phrase)
    type = identify_type(phrase)
    RESPONSES[type]
  end

  def identify_type(phrase)
    return :default unless phrase.is_a? String

    case phrase
    when /^(?=.*[A-Z])[^a-z]+$/
      :forceful
    when /^[^\n]+\?$/
      :question
    when /^\s*$/
      :address
    else
      :default
    end
  end
end
