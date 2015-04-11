class Bob
  def hey( input )
    @sentence = input

    case
    when shouting?
      'Whoa, chill out!'
    when question?
      'Sure.'
    when silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def shouting?
    @sentence =~ /[A-Z]/ and @sentence.upcase == @sentence
  end

  def question?
    @sentence.end_with? '?'
  end

  def silence?
    @sentence.strip.empty?
  end
end
