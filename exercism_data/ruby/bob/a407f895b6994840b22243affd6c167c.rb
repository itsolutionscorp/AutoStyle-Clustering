require_relative 'listening'

class Bob
  include Listening

  def hey(speech)
    if shouting(speech)
      "Woah, chill out!"
    elsif silence(speech)
      "Fine. Be that way!"
    elsif question(speech)
      "Sure."
    else
      "Whatever."
    end
  end
end
