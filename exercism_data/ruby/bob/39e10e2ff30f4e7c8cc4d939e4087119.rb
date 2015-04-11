class Bob

  def hey(sentence)
    Sentence.new(self, sentence).respond
  end

  def response(response_type)
    case response_type
      when :silence
        'Fine. Be that way!'
      when :shouting
        'Woah, chill out!'
      when :question
        'Sure.'
      else
        'Whatever.'
    end
  end

end

class Sentence

  def initialize(person, sentence)
    @person = person
    @sentence = sentence || ''
  end

  def respond
    if silence?
      response_type = :silence
    elsif shouting?
      response_type = :shouting
    elsif question?
      response_type = :question
    end

    @person.response(response_type)
  end

  def silence?
    @sentence.empty?
  end

  def shouting?
    @sentence.upcase == @sentence
  end

  def question?
    @sentence.end_with? '?'
  end

end
