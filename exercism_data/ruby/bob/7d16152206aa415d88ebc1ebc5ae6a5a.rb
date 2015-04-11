class Person
 def hey(msg)
    case
      when (msg.nil? or msg.empty?)
        @responses[:empty]
      when (msg == msg.upcase)
        @responses[:shouthing]
      when (msg.end_with? "?")
        @responses[:question]
      else
        @responses[:default]
    end
  end
end

class Bob < Person
  def initialize
    @responses = {
        empty:      "Fine. Be that way!",
        shouthing:  "Woah, chill out!",
        question:   "Sure.",
        default:    "Whatever."
    }
  end
end
