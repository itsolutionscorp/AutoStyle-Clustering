class Utterance
  attr_reader :utterance
  OTHER, QUESTION, SILENT, YELL = (1..4).to_a

  def initialize(string)
    @utterance = string.to_s
  end

  def type
    if utterance.empty?
      SILENT
    elsif utterance.match(/[A-Z]/) && utterance.upcase == utterance
      YELL
    elsif utterance.end_with?('?')
      QUESTION
    else
      OTHER
    end
  end                                                   
end

class Bob
  REPERTOIRE =
  {
    Utterance::OTHER    => "Whatever.",
    Utterance::QUESTION => "Sure.",
    Utterance::SILENT   => "Fine. Be that way!",
    Utterance::YELL     => "Woah, chill out!",
  }

  def hey(string)
    REPERTOIRE.fetch(Utterance.new(string).type)
  end
end
