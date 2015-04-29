# bob.rb

class Bob
  def hey sentence
    @sentence = sentence.strip
    return 'Fine. Be that way!' if silence?
    return 'Whoa, chill out!' if shouting?
    return 'Sure.' if question?
    'Whatever.'
  end

  def silence?
    @sentence.empty?
  end

  def shouting?
    @sentence.upcase == @sentence && @sentence.match(/[a-zA-Z]/)
  end

  def question?
    @sentence.end_with? '?'
  end

end
