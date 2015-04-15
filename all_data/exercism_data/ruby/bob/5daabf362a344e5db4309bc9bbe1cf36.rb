class Bob
  def hey(message)
    return 'Sure.' if question? message.clone
    return 'Woah, chill out!' if yelling? message.clone
    return 'Fine. Be that way!' if nothing? message.clone
    'Whatever.'
  end

  def nothing?(msg)
    msg.strip.empty?
  end

  def yelling?(msg)
    unless nothing? msg
      msg.upcase!.nil? if contain_letters? msg
    end
  end

  def question?(msg)
    msg.gsub("\n", '') =~ /\?$/ unless yelling?(msg)
  end

  private

  def contain_letters?(msg)
    msg =~ /[a-zA-Z]/
  end
end
