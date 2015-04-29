class Bob

  class Message < String
    def shouting?
      upcase == self
    end

    def asking_a_question?
      end_with? '?'
    end
  end

  def hey(message)
    m = Message.new(message || '')
    case
    when m.empty? then "Fine. Be that way."
    when m.shouting? then "Woah, chill out!"
    when m.asking_a_question? then "Sure."
    else "Whatever."
    end
  end

end
