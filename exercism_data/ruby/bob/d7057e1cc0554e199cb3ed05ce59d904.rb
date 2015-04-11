class Bob

  def hey(sntc)
    sentence = Sentence.new(sntc)
    case
    when sentence.is_silence? then 'Fine. Be that way!'
    when sentence.is_shout? then 'Woah, chill out!'
    when sentence.is_question? then 'Sure.'
    else 'Whatever.'
    end
  end

  class Sentence

    def initialize(sentence)
      @sentence = sentence
    end

    def is_question?
      @sentence[-1] == '?'
    end

    def is_shout?
      @sentence =~ /[a-bA-Z]/ && @sentence == @sentence.upcase
    end

    def is_silence?
      @sentence.strip.empty?
    end

  end

end
