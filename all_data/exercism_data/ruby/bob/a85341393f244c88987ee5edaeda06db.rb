class Bob
  def initialize
    @response = Hash[
        :toYelling      => "Woah, chill out!",
        :toQuestion     => "Sure.",
        :toSilence      => "Fine. Be that way!",
        :toAnythingElse => "Whatever."
    ]
  end

  def hey (sentence)
    case
      when yelled?(sentence)
        response[:toYelling]
      when asked?(sentence)
        response[:toQuestion]
      when nothingSaid?(sentence)
        response[:toSilence]
      else
        response[:toAnythingElse]
    end
  end

  private
  attr_reader :response

  def asked?(sentence)
    sentence.end_with?('?')
  end

  def yelled? (sentence)
    sentence.match(/[a-zA-Z]/) != nil and sentence.upcase == sentence
  end

  def nothingSaid?(sentence)
    sentence.strip == ""
  end
end
