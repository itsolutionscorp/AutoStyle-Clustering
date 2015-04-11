# encoding: utf-8

class Bob
  def hey appeal
    if shouting?(appeal)
      return 'Woah, chill out!'
    elsif question?(appeal)
      return 'Sure.'
    elsif no_statement?(appeal)
      return 'Fine. Be that way!'
    else
      return 'Whatever.'
    end
  end

  protected
  def question? appeal
    appeal.match(/\?\Z/)
  end

  def shouting? appeal
    appeal.match(/\A[^a-z]+\Z/) && appeal.match(/[A-Z]/)
  end

  def no_statement? appeal
    appeal.match(/\A\s*\Z/)
  end
end
