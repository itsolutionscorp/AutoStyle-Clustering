class Bob
  def hey( input )
    sentence = Sentence.new input

    case
    when sentence.shouting?
      'Whoa, chill out!'
    when sentence.question?
      'Sure.'
    when sentence.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  class Sentence
    attr_reader :sentence

    def initialize( input )
      @sentence = input
    end

    def shouting?
      sentence =~ /[A-Z]/ and sentence.upcase == sentence
    end

    def question?
      sentence.end_with? '?'
    end

    def silence?
      sentence.strip.empty?
    end
  end
end
