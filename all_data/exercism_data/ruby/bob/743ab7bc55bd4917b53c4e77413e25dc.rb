class Bob
  def hey(msg)
    msg = msg.to_s
    return 'Fine. Be that way.' if silent?   msg
    return 'Woah, chill out!'   if shouting? msg
    return 'Sure.'              if asking?   msg
    'Whatever.'
  end

  private

    def silent?(msg)
      msg.empty?
    end

    def shouting?(msg)
      msg.upcase == msg
    end

    def asking?(msg)
      msg.end_with? '?'
    end
end
