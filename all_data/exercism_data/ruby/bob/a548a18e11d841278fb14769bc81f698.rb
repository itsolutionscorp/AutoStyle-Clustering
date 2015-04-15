# encoding: utf-8

class Bob
  NO_LOWER_SPACE = '[^\p{Ll}]'

  def hey(what)
    case what.to_s
    when /^\s*$/u
      'Fine. Be that way!'
    when /^#{NO_LOWER_SPACE}+$/ou
      'Woah, chill out!'
    when /\?$/u
      'Sure.'
    else
      'Whatever.'
    end
  end
end
