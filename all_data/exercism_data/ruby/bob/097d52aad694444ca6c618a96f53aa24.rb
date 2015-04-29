class Bob
  def hey(input)
    input = Input.new(input)

    case
    when input.silent?
      'Fine. Be that way.'
    when input.shout?
      'Woah, chill out!'
    when input.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  class Input
    attr_reader :string

    def initialize(string)
      @string = string.to_s.strip
    end

    def silent?
      string.empty?
    end

    def shout?
      string.upcase == string
    end

    def question?
      string.end_with?('?')
    end
  end
end
