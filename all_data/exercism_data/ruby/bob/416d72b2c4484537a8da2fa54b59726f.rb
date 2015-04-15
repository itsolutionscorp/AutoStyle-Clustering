# encoding: utf-8

class Bob
  NO_LOWER_SPACE = '[^\p{Ll}]'

  def hey(what)
    case what.to_s
    when empty?
      'Fine. Be that way!'
    when shouting?
      'Woah, chill out!'
    when question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  def empty?
    /^\s*$/u
  end

  def shouting?
    /^#{NO_LOWER_SPACE}+$/ou
  end

  def question?
    /\?$/u
  end
end
