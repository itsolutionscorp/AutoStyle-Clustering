# encoding: utf-8

class Bob
  def hey appeal
    if appeal.match(/\A[^a-z]+\Z/) && appeal.match(/[A-Z]/) # shouting is no lowercase letter and at least one uppercase letter
      return 'Woah, chill out!'
    elsif appeal.match(/\?\Z/) # question
      return 'Sure.'
    elsif appeal.match(/\A\s*\Z/) # no statement
      return 'Fine. Be that way!'
    else
      return 'Whatever.'
    end
  end
end
