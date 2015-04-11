# encoding: utf-8

class Bob
  def hey(text)
    case text
    when nil, /^[[:blank:]]*$/
      'Fine. Be that way!'
    when /^[[[:space:]][[:digit:]][[:upper:]][[:punct:]]]+$/
      'Woah, chill out!'
    when /\?$/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
