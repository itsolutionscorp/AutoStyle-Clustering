class Bob
  def hey(string)
    prompt = Prompt.new(string)
    return 'Fine. Be that way!' if prompt.silence?
    return 'Woah, chill out!' if prompt.shout?
    return 'Sure.' if prompt.question?
    'Whatever.'
  end

  private

  class Prompt
    attr_reader :string

    def initialize(string)
      @string = string
    end

    def silence?
      blank?
    end

    def question?
      string[-1] == "?"
    end

    def shout?
      string == string.upcase
    end

    private

    def blank?
      string.to_s =~ /^\s*$/
    end
  end
end
