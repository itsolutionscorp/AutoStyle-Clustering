class Bob
  def hey(sntc)
    sentence = Sentence.new(sntc)
    case
    when sentence.silence? then 'Fine. Be that way!'
    when sentence.shout? then 'Woah, chill out!'
    when sentence.question? then 'Sure.'
    else 'Whatever.'
    end
  end
end

class Bob
  class Sentence
    def initialize(sentence)
      @sentence = sentence
    end

    def question?
      @sentence[-1] == '?'
    end

    def shout?
      @sentence =~ /[A-Z]/ && @sentence == @sentence.upcase
    end

    def silence?
      @sentence.strip.empty?
    end
  end
end
