class Bob
  def hey(sentence)
    sentence = sentence.to_s
    case
    when silent?(sentence)   then 'Fine. Be that way.'
    when shouting?(sentence) then 'Woah, chill out!'
    when question?(sentence) then 'Sure.'
    else 'Whatever.'
    end
  end

  private

  def silent?(sentence)
    sentence.empty?
  end

  def shouting?(sentence)
    sentence == sentence.upcase
  end

  def question?(sentence)
    sentence.end_with? '?'
  end
end
