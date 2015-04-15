class Bob
  ANSWER_TO = {
    question: 'Sure.',
    yell: 'Woah, chill out!',
    address: 'Fine. Be that way!',
    anything: 'Whatever.'
  }

  def hey sentence
    case
    when sentence[-1] == '?'
      ANSWER_TO[:question]
    when sentence == sentence.upcase
      ANSWER_TO[:yell]
    when sentence.rstrip.empty?
      ANSWER_TO[:address]
    else
      ANSWER_TO[:anything]
    end
  end
end
