module L337
  BRO_PREFIX = "Bro, " # The prefix that lets those in the know know that the speaker is a member of the 1337.
end

module L337::Responder

  def to_l33t(message)
    # TODO support complex translations
    to_translate = message.split(L337::BRO_PREFIX)[-1]
    downcased = to_translate.downcase
    downcased.tr(*%w(
      a 4
      e 3
      p P
    ).each_slice(2).to_a.transpose.map(&:join))
  end
end

module MessageHelpers
  def nothing?
    empty?
  end

  def asking?
    end_with? '?'
  end

  def shouting?
    upcase == self
  end

  def bro?
    start_with? L337::BRO_PREFIX
  end
end


class Bob
  include L337::Responder

  def hey(message)
    message.extend(MessageHelpers)

    case
    when message.nothing?
      'Fine. Be that way.'
    when message.bro?
      to_l33t(message)
    when message.asking?
      'Sure.'
    when message.shouting?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

end
