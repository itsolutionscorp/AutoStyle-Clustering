class Bob
  def hey(sentence)
    intonation = Intonation.new sentence
    return case 
      when intonation.nothing? then 'Fine. Be that way!'
      when intonation.shouting? then 'Woah, chill out!'
      when intonation.question? then 'Sure.'
      else 'Whatever.'
    end
  end
end

class Intonation
  attr_reader :sentence

  def initialize(sentence)
    @sentence = sentence.to_s
  end

  def nothing?
    sentence.empty?
  end

  def shouting?
    sentence == sentence.upcase
  end

  def question?
    sentence.end_with? '?'
  end
end
