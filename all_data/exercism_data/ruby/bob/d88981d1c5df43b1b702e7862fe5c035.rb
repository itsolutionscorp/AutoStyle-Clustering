class Bob
  def hey words
    conversation = Conversation.new words
    if conversation.shouting?
      'Woah, chill out!'
    elsif conversation.asking?
      'Sure.'
    elsif conversation.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  class Conversation < Struct.new(:words)
    def shouting?
      words =~/[A-Z]/ && words == words.upcase
    end

    def asking?
      words.chars.last == '?'
    end

    def silence?
      words =~ /\A *\Z/
    end
  end
end
