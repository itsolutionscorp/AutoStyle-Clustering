class Bob
  def hey(words)
    sentence = Sentence.new(words)
    return 'Fine. Be that way!' if sentence.is_silence?
    return 'Woah, chill out!'   if sentence.is_shouting?
    return 'Sure.'              if sentence.is_a_question?
    return 'Whatever.'
  end
end

class Sentence
  def initialize(words)
    @sentence = words
  end

  def is_a_question?
    @sentence.end_with? '?'
  end

  def is_shouting?
    /[a-z]/.match(@sentence).nil?
  end

  def is_silence?
    @sentence.nil? or @sentence !~ /[^[:space:]]/
  end
end
