class Bob
  def hey(input)
    input = Input.new(input)

    case
    when input.is_silent?
      'Fine. Be that way.'
    when input.is_shout?
      'Woah, chill out!'
    when input.is_question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  class Input
    attr_reader :string

    def initialize(string)
      @string = string.to_s
    end

    def is_silent?
      string.strip.empty?
    end

    def is_shout?
      string !~ /[a-z]+/
    end

    def is_question?
      string =~ /\?$/
    end
  end
end
