# encoding: UTF-8

class Bob
  def hey(said)
    case
    when they_are_too_quiet?(said)
      'Fine. Be that way!'
    when they_need_to_chill?(said)
      'Woah, chill out!'
    when for_sure?(said)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def they_need_to_chill? said
    (said == said.upcase && said =~ /[A-z]/)
  end

  def for_sure? said
    said =~ /\?\Z/
  end

  def they_are_too_quiet? said
    said =~ /\A\s*\Z/
  end
end
