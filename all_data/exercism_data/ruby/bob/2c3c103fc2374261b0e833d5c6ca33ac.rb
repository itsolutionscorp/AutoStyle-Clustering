class Bob

  def initialize
    @brain = TeenBrain.new
  end

  def hey(sentence)
    @brain.choose_answer(sentence)
  end

  class TeenBrain

    def choose_answer(sentence)
      case sentence
      when silence? then 'Fine. Be that way!'
      when shout? then 'Woah, chill out!'
      when question? then 'Sure.'
      else 'Whatever.'
      end
    end

    def shout?
      ->(sentence) { sentence == sentence.upcase }
    end

    def question?
      ->(sentence) { sentence.end_with?('?') }
    end

    def silence?
      ->(sentence) { sentence.nil? || sentence.strip.empty? }
    end
  end
end
