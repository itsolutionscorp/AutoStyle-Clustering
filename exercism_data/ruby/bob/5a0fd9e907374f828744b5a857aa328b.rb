class Bob

  REPLIES = { :shock     => 'Woah, chill out!',
              :agreement => 'Sure.',
              :anger     => 'Fine. Be that way!',
              :boredom   => 'Whatever.'}

  # The #hey method allows rudimentary communication with a teenager.
  def hey(input)
    heard = BlahBlahBlah.new(input)

    (express :shock     if heard.shouting?) or
    (express :agreement if heard.question?) or
    (express :anger     if heard.silence? ) or
    (express :boredom)
  end

  private

  def express(mood)
    REPLIES.fetch(mood)
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
