class Bob
  def hey(sentence)
    @sentence = sentence.to_s
    case
    when silent? then 'Fine. Be that way.'
    when shouting? then 'Woah, chill out!'
    when question? then 'Sure.'
    else 'Whatever.'
    end
  end

  private

  def silent?;   @sentence.empty?              end
  def shouting?; @sentence == @sentence.upcase end
  def question?; @sentence.end_with? '?'       end
end
