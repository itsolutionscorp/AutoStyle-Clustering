class Bob
  def hey(mouthnoises)
    utterance = Utterance.new(mouthnoises)
    case
    when utterance.silent?
      "Fine. Be that way!"
    when utterance.loud?
      "Woah, chill out!"
    when utterance.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Utterance
  def initialize(mouthnoises)
    @mouthnoises = mouthnoises.to_s
  end

  def silent?
    @mouthnoises.empty?
  end

  def loud?
    @mouthnoises.upcase == @mouthnoises
  end

  def question?
    @mouthnoises.end_with? "?"
  end
end
