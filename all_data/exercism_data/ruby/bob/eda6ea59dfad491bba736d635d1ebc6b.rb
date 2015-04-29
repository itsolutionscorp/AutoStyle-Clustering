# encoding: UTF-8

class Bob
  def hey(said)
    case
    when they_are_too_quiet?(said) then say 'Fine. Be that way!'
    when they_need_to_chill?(said) then say 'Woah, chill out!'
    when thats_for_sure?(said) then say 'Sure.'
    else say 'Whatever.'
    end
  end

  private

  def they_need_to_chill?(heard)
    (heard == heard.upcase && heard =~ /[A-z]/)
  end

  def thats_for_sure?(heard)
    heard =~ /\?\Z/
  end

  def they_are_too_quiet?(heard)
    heard =~ /\A\s*\Z/
  end

  def say(something)
    something
  end
end
