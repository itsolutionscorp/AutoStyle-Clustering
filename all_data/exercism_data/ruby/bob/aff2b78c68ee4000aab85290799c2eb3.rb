class Bob
  module SpeechRecognition
    def shout?
      not silence? and self == upcase
    end

    def question?
      not shout? and end_with?('?')
    end

    def silence?
      strip == ''
    end
  end

  def hey(phrase)
    phrase.extend SpeechRecognition

    return 'Fine. Be that way!'  if phrase.silence?
    return 'Woah, chill out!'    if phrase.shout?
    return 'Sure.'               if phrase.question?
    return 'Whatever.'
  end
end
