# -*- coding: utf-8 -*-
class Bob
  def hey(message)
    if is_yell(message)
      'Woah, chill out!'
    elsif is_question(message)
      'Sure.'
    elsif is_saying_nothing(message)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def is_question(message)
    message.end_with?('?')
  end

  def is_yell(message)
    # It should have at least one upper case letter and
    # should not have any lower case letter.
    (message =~ /[A-Z]/) && ! (message =~ /[a-z]/)
  end

  def is_saying_nothing(message)
    message.strip == ''
  end
end
