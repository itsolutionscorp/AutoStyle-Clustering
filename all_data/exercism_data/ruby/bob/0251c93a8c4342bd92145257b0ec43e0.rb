class Bob
  ANSWERS = {
    shout: 'Woah, chill out!',
    question: 'Sure.',
    regular: 'Whatever.',
    silence: 'Fine. Be that way!'
  }

  attr_accessor :phrase

  def hey(phrase)
    self.phrase = phrase

    ANSWERS[ phrase_type ]
  end

  def phrase_type
    case
      when silence?
        :silence
      when shout?
        :shout
      when question?
        :question
      else
        :regular
    end
  end

  def silence?
    phrase.strip.empty?
  end

  def shout?
    !english_text.empty? && english_text == english_text.upcase
  end

  def question?
    phrase.end_with?('?')
  end

  def english_text
    phrase.gsub(/[^A-Za-z]/, '')
  end
end
