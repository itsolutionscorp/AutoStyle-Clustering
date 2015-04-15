class Bob
  # The #hey method allows rudimentary communication with a teenager.
  def hey(input)
    heard = BlahBlahBlah.new(input)

    catch(:reply) do
      say('Woah, chill out!') if heard.shouting?

      say('Sure.') if heard.question?

      say('Fine. Be that way!') if heard.silence?

      say('Whatever.')
    end
  end

  private

  def say(phrase)
    throw :reply, phrase
  end

  # This class represents something said to a teenager.
  # Whatever you say, they just hear "blah, blah, blah"
  # but they can recognize tone.
  class BlahBlahBlah
    attr_reader :input

    def initialize(input)
      @input = input.to_s
    end

    def shouting?
      !silence? && input.upcase == input
    end

    def question?
      input.end_with? '?'
    end

    def silence?
      input == ''
    end
  end
end
