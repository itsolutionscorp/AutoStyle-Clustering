# maybe overkill, but trying Avdi Grimm's suggestion to use conversion methods
module Dialogues
  module Conversions
    def Dialogue(text)
      case text
      when /.*\?$/
        Question.new
      when /^[A-Z\b\W\d]+$/
        Confrontation.new
      when ->(val){String(val) == "" }
        Silence.new
      else
        Default.new
      end
    end
  end

  class Question
    def answer
      'Sure.'
    end
  end

  class Confrontation
    def answer
      'Woah, chill out!'
    end
  end

  class Silence
    def answer
      'Fine. Be that way.'
    end
  end

  class Default
    def answer
      'Whatever.'
    end
  end
end

class Bob
  include Dialogues
  include Dialogues::Conversions

  def hey(text)
    dialogue = Dialogue(text)
    dialogue.answer
  end
end
