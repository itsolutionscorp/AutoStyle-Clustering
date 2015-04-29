class Bob
  attr_reader :message

  def hey(*args)
    @message = args.join

    return 'Fine. Be that way!' if empty_or_only_spaces?
    return 'Woah, chill out!' if yell? && isalpha?
    return 'Sure.' if question?
    'Whatever.'
  end

  def question?
    @message[-1,1] == ??
  end

  def yell?
    @message.upcase == @message
  end

  def isalpha?
    @message.match(/[[:alpha:]]/)
  end

  def empty_or_only_spaces?
    @message.strip.empty?
  end
end
