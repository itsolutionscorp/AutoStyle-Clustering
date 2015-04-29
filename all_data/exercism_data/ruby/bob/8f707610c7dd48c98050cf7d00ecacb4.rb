class Bob
  ANSWERS = { question: 'Sure.',
              yell: 'Whoa, chill out!',
              nothing: 'Fine. Be that way!',
              anything: 'Whatever.'}

  def hey phrase
    case
    when is_yell?(phrase)
      ANSWERS[:yell]
    when phrase.end_with?('?')
      ANSWERS[:question]
    when phrase.lstrip.empty?
      ANSWERS[:nothing]
    else
      ANSWERS[:anything]
    end
  end

  def is_yell? phrase
    characters?(phrase) && phrase.upcase == phrase
  end

  def characters? phrase
    !phrase.scan(/\S\w\D/).empty?
  end
end
