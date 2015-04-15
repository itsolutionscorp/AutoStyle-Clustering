class Bob
  BRO_PREFIX = "Bro, "

  def hey(message)
    case
    when is_nothing?(message)
      'Fine. Be that way.'
    when is_bro?(message)
      to_l33t(message)
    when is_asking?(message)
      'Sure.'
    when is_shouting?(message)
      'Woah, chill out!'
    when is_telling?(message)
      'Whatever.'
    else
      raise "I don't know how to respond to: #{message}"
    end
  end

  private

  def is_nothing?(message)
    message.empty?
  end

  def is_asking?(message)
    message.end_with?('?')
  end

  def is_shouting?(message)
    message.upcase == message
  end
  def is_telling?(message)
    %w(. !).include? message[-1]
  end

  def is_bro?(message)
    message.start_with? BRO_PREFIX
  end

  def to_l33t(message)
    # TODO support complex translations
    to_translate = message.split(BRO_PREFIX)[-1]
    downcased = to_translate.downcase
    downcased.tr(*%w(
      a 4
      e 3
      p P
    ).each_slice(2).to_a.transpose.map(&:join))
  end

  # p.s. What is exercism.io for?
  #
  # i just saw your tweet thanking franklin & zach;
  # thought i'd check it out;
  # now here i am writing some ruby and JS and l33t!
  #
  # p.p.s. it's 4w3s0m3 you're on the ruby rogues now
end
