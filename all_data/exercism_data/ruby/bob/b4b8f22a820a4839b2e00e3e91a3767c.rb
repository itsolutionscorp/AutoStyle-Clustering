# Bob
# Bob is a lackadaisical teenager. In conversation, his responses are very limited.
# Bob answers 'Sure.' if you ask him a question.
# He answers 'Woah, chill out!' if you yell at him.
# He says 'Fine. Be that way!' if you address him without actually saying anything.
# He answers 'Whatever.' to anything else.

class Bob

  def hey(message)
    case 
      when silent?(message)
        return 'Fine. Be that way!'
      when yelling?(message)
        return 'Woah, chill out!'
      when question?(message)
        return 'Sure.'
      else
        return 'Whatever.'
    end
  end

  private

  def silent?(message)
    message.strip.empty?
    #ALTERNATIVE: message.delete(' ').empty?
    #ALTERNATIVE: message.gsub(/\s+/, '').empty?
  end

  def yelling?(message)
    message == message.upcase and message != message.swapcase
    #ALTERNATIVE: message == message.upcase and message.match(/[A-Z]/)
  end

  def question?(message)
    message.end_with?('?')
  end

end
