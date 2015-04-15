class Bob
  def hey(words)
    Response.hey(words)
  end

  class Response
    attr_reader :words

    def self.hey(words)
      self.new(words).hey
    end

    def initialize(words)
      @words = words
    end

    def hey
      case
      when silence? then silent_response
      when question? then question_response
      when shouting? then shouting_response
      else statement_response
      end
    end

    def silence?
      words.to_s == ''
    end

    def question?
      punctuation(words) == '?'
    end

    def shouting?
      words.upcase == words
    end

    def statement_response
      'Whatever.'
    end

    def shouting_response
      'Woah, chill out!'
    end

    def question_response
      'Sure.'
    end

    def silent_response
      'Fine. Be that way.'
    end

    def punctuation(words)
      words[-1]
    end
  end
end
