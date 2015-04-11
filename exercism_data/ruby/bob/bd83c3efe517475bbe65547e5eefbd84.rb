class Bob

  attr_reader :sentence

  def hey(sentence)
    @sentence = sentence
    case
    when shouting? then 'Woah, chill out!'
    when question? then 'Sure.'
    when silence?  then "Fine. Be that way!"
    else
      'Whatever.'
    end
  end

  def shouting?
    (sentence.upcase == sentence) && (sentence =~ /[A-Z]/)
  end

  def question?
    sentence.end_with?("?")
  end

  def silence?
    sentence.empty? || sentence =~ /^\s+$/
  end
  
end
