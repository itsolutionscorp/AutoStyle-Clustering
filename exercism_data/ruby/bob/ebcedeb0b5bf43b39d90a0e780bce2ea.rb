class Bob

  def hey(sentence)
    case sentence.split.join
    when empty    then 'Fine. Be that way!'
    when shout    then 'Woah, chill out!'
    when question then 'Sure.'
    else 'Whatever.'
    end
  end

  private

  def empty
    /^\s*$/
  end

  def shout
    /^[^a-z]*$/
  end

  def question
    /\?$/
  end

end
