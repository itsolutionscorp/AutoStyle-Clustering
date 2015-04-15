module Person
 def hey(msg)
    case
      when (msg.nil? or msg.empty?)
        @responses[:empty]
      when (msg == msg.upcase)
        @responses[:shouting]
      when (msg.end_with? "?")
        @responses[:question]
      else
        @responses[:default]
    end
  end
end

class Bob
  include Person
  def initialize
    @responses = {
        empty:      "Fine. Be that way!",
        shouting:   "Woah, chill out!",
        question:   "Sure.",
        default:    "Whatever."
    }
  end
end
