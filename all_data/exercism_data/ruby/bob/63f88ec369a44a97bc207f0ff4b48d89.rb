class Bob
  def hey(greeting)
    analysis = Analyzer.new(greeting)
    
    return 'Woah, chill out!'   if analysis.is_shouting?
    return 'Sure.'              if analysis.is_question?
    return 'Fine. Be that way!' if analysis.is_silence?
    'Whatever.'
  end

end

class Analyzer
  def initialize(sentence)
    @sentence = sentence
  end

  def is_shouting?
    has_words? && is_all_caps?
  end

  def is_question?
    @sentence.end_with? '?'
  end

  def is_silence?
    @sentence.strip == ''
  end

  private
  
  def has_words?
    @sentence.match(/[[:alpha:]]/)
  end
  
  def is_all_caps?
    @sentence.upcase == @sentence
  end
end
