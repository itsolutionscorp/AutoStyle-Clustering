class Bob
  def hey( sentence )
    case
    when is_shouting?(sentence)
      'Whoa, chill out!'
    when is_a_question?(sentence)
      'Sure.'
    when is_silence?(sentence)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def is_shouting?( sentence )
    sentence =~ /[A-Z]/ and sentence.upcase == sentence
  end

  def is_a_question?( sentence )
    sentence.end_with? '?'
  end

  def is_silence?( sentence )
    sentence.strip.empty?
  end
end
