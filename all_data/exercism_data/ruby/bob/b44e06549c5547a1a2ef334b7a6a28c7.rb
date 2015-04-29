class Bob
  class Phrase
    attr_reader :text
    private :text

    def initialize(text)
      @text = text
    end

    def style
      return :silence  if silence?
      return :shouting if shouting?
      return :question if question?
      :regular
    end

    private

    def question?
      text[-1] == '?'
    end

    def shouting?
      text.upcase == text
    end

    def silence?
      text.strip == ''
    end
  end

  RESPONSES = {
    silence:  'Fine. Be that way!',
    shouting: 'Woah, chill out!',
    question: 'Sure.'
  }.freeze

  DEFAULT_RESPONSE = 'Whatever.'

  def hey(text)
    RESPONSES.fetch(Phrase.new(text).style, DEFAULT_RESPONSE)
  end
end
