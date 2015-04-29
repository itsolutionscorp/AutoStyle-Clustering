class Bob
  String.class_eval do
    def question
      self[0, length-1] + '?'
    end
  end

  def hey(ask)
    ask = ask.strip

    case ask
    when ''
      'Fine. Be that way!'
    when ask.upcase
      'Woah, chill out!'
    when ask.question
      'Sure.'
    else
      'Whatever.'
    end
  end
end
