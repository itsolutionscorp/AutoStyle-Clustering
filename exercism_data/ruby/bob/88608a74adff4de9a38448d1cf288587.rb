class Bob
  def hey(sentence)
    return 'Fine. Be that way!' if Sentence.is_silence? sentence
    return 'Woah, chill out!'   if Sentence.is_shouting? sentence
    return 'Sure.'              if Sentence.is_a_question? sentence
    return 'Whatever.'
  end
end

class Sentence
  def self.is_a_question?(sentence)
    sentence[-1] == '?'
  end

  def self.is_shouting?(sentence)
    /[a-z]/.match(sentence).nil?
  end

  def self.is_silence?(sentence)
    sentence.nil? or sentence !~ /[^[:space:]]/
  end
end
