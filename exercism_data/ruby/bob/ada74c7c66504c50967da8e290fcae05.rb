class Bob
  def hey(message)
    @message = message

    return 'Fine. Be that way!' if empty?
    return 'Woah, chill out!' if yelling?
    return 'Sure.' if question?
    return 'Whatever.'
  end

private

  def yelling?
    all_upcase? && has_letters?
  end

  def has_letters?
    !(@message =~/[a-zA-Z]/i).nil?
  end

  def all_upcase?
    @message ==  @message.upcase
  end

  def question?
    @message.end_with? '?'
  end

  def empty?
    @message !~ /\S/
  end
end
