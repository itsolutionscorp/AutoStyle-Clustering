class Bob
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
      'Sure.'
    elsif input.is_shout?
      'Woah, chill out!'
    elsif input.is_empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
