class Response
  attr_reader :input_text

  def initialize(text)
    @input_text = text
  end

  def self.utterance_matches?(text)
    # Filled out by subclasses
  end

  def text
    # Filled out by subclasses
  end
end

class Person
  def self.kinds_of_utterances
    [] # Filled out by subclass
  end

  def self.response_for_text(text)
    self.kinds_of_utterances.each do |kind_of_speech|
      if kind_of_speech.matches? text
        return kind_of_speech.new(text)
      end
    end
  end
end

module Teenager
  class Person < ::Person
    def self.kinds_of_utterances
      [Silence, Shouting, Question, Statement]
    end
  end

  class Statement < Response
    def self.matches?(text)
      true 
    end

    def text
      'Whatever.'
    end
  end

  class Question < Response
    def self.matches?(text)
      text[-1] == '?'
    end
    def text
      'Sure.'
    end
  end

  class Silence < Response
    def self.matches?(text)
      text == '' || !!text.match(/^[\s]+$/)
    end

    def text
      'Fine. Be that way!'
    end
  end

  class Shouting < Response
    def self.matches?(text)
      cleaned_text = text.gsub(/[\d\s,!?%\*@#\$\(\*\^]/, '')
      !!cleaned_text.match(/^[A-Z]+$/)
    end

    def text
      'Woah, chill out!'
    end
  end
end

class Bob
  def hey(text)
    response = Teenager::Person.response_for_text(text)
    response.text
  end
end
