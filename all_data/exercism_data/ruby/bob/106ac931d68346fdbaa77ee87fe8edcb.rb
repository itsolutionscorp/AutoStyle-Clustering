class Bob
  BRO_PREFIX = "Bro, "

  def hey(message)
    case
    when nothing?(message)
      'Fine. Be that way.'
    when bro?(message)
      to_l33t(message)
    when asking?(message)
      'Sure.'
    when shouting?(message)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private

  def nothing?(message)
    message.empty?
  end

  def asking?(message)
    message.end_with?('?')
  end

  def shouting?(message)
    message.upcase == message
  end
  def telling?(message)
    %w(. !).include? message[-1]
  end

  def bro?(message)
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
