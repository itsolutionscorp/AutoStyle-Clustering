module Brain
  module Responses
    class Person
      def default
        'Thank you.'
      end
      def shouting
        'There is no need to shout.'
      end
      def question
        'That is a great question.'
      end
      def silence
        'I am sorry, have I done something to offend you?'
      end
    end
  end
end

module Brain
  module Responses
    class Teenager < Person
      def default
        'Whatever.'
      end
      def shouting
        'Woah, chill out!'
      end
      def question
        'Sure.'
      end
      def silence
        'Fine. Be that way!'
      end
    end
  end
end

module Brain
  class SpeechProcessor
    def initialize(response_strategy)
      @responses = response_strategy
    end
    def listen(prompt)
      @prompt = prompt
      self
    end
    def respond
      return @responses.silence if silence?
      return @responses.shouting if shouting?
      return @responses.question if question?
      return @responses.default
    end
    def shouting?
      (@prompt == @prompt.upcase && @prompt != @prompt.downcase)
    end
    def question?
      @prompt.end_with?('?')
    end
    def silence?
      @prompt.strip.empty?
    end
    private :shouting?, :question?, :silence?
  end
end

class Person
  def initialize
    @processor = Brain::SpeechProcessor.new(response_strategy)
  end
  def hey(prompt)
    @processor.listen(prompt).respond
  end
  def listen(prompt)
    @processor.set_prompt(prompt)
  end
  def response_strategy
    Brain::Responses::Person.new
  end
  private :response_strategy
end

class Teenager < Person
  def response_strategy
    Brain::Responses::Teenager.new
  end
  private :response_strategy
end

class Bob < Teenager

end
