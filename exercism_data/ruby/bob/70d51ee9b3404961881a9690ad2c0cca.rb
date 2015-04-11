# encoding: utf-8

class Bob
  def hey appeal
    return 'Woah, chill out!' if shouting?(appeal)
    return 'Sure.' if question?(appeal)
    return 'Fine. Be that way!' if no_statement?(appeal)
    return 'Whatever.'
  end

  protected
  def question? appeal
    appeal.end_with?("?")
  end

  def shouting? appeal
    appeal.match(/\A[^a-z]+\Z/) && appeal.match(/[A-Z]/)
  end

  def no_statement? appeal
    appeal.match(/\A\s*\Z/)
  end
end
