module Answering
  def hey(sentence)
    answer sentence
  end

  protected

  def answer(sentence)
    responses.detect(-> { [:default, default_answer] }) { |action, response| SpeechRecognizer.send action, sentence }[1]
  end
end

class Roger
  include Answering

  def responses
    {
      :silent?    => "Are you okay?",
      :screaming? => "Please, calm down.",
      :asking?    => "Sorry, I can't help you.",
    }
  end

  def default_answer
    "Thats interesting!"
  end
end

class Bob
  include Answering

  def responses
    {
      :silent?    => "Fine. Be that way!",
      :screaming? => "Woah, chill out!",
      :asking?    => "Sure.",
    }
  end

  def default_answer
    "Whatever."
  end
end

module SpeechRecognizer
  def self.silent?(sentence)
    sentence.to_s.empty?
  end

  def self.screaming?(sentence)
    sentence == sentence.upcase
  end

  def self.asking?(sentence)
    sentence.end_with? "?"
  end
end
