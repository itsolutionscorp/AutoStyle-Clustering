class Bob
  module Response
    QUESTION = 'Sure.'
    YELL = 'Woah, chill out!'
    EMPTY = 'Fine. Be that way!'
    OTHER = 'Whatever.'
  end

  class Input
    attr_reader :input
    def initialize(str)
      @input = str
    end

    def is_question?
      !self.is_shout? && self.input.end_with?('?')
    end

    def is_shout?
      !self.is_empty? && self.input.upcase == self.input
    end

    def is_empty?
      self.input.strip.empty?
    end
  end

  def hey(str)
    input = Input.new(str)
    if input.is_question?
      Response::QUESTION
    elsif input.is_shout?
      Response::YELL
    elsif input.is_empty?
      Response::EMPTY
    else
      Response::OTHER
    end
  end
end
