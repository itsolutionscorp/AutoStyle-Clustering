class Request
  attr_accessor :sentence
  def initialize(sentence)
    self.sentence = sentence
  end

  def to_s
    sentence
  end

  def silence?
    sentence.nil? || sentence == ""
  end

  def yelling?
    sentence == sentence.upcase
  end

  def question?
    sentence =~ /\?$/ 
  end
end

class Response
  def self.for(request)
    new(request).generate_response
  end

  def generate_response
    case request
    when silence  then "Fine. Be that way."
    when yelling  then "Woah, chill out!"
    when question then "Sure."
    else "Whatever."
    end
  end

  private

    attr_accessor :request
    def initialize(request)
      self.request = request
    end

    def silence
      ->(sentence){ sentence.silence? }
    end

    def yelling
      ->(sentence){ sentence.yelling? }
    end

    def question
      ->(sentence) { sentence.question? }
    end
end

class Bob
  def hey(sentence)
    Response.for Request.new(sentence)
  end
end
