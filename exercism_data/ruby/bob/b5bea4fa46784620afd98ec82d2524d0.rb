class Bob
  def hey(cue)
    case Tone.new(cue)
    when lambda(&:silence?)  then 'Fine. Be that way!'
    when lambda(&:shouting?) then 'Woah, chill out!'
    when lambda(&:question?) then 'Sure.'
    else 'Whatever.'
    end
  end

  class Tone < Struct.new(:cue)
    def silence?
      cue =~ /\A\s*\Z/
    end

    def shouting?
      cue =~ /\A[^a-z]+\Z/ && cue =~ /[A-Z]/
    end

    def question?
      cue =~ /\?\Z/
    end
  end
end
