#I apologize, in advance, for this code. I am a noob, hopefully this will help!
module Words
  def stating_something words
    'Whatever.'
  end
  def shouting? words
    words == words.upcase && !words.empty?
  end
  def asking_question? words
    words.end_with?('?')
  end
  def empty_or_nil? words
    words.to_s == ''
  end
end

class Teenager
  include Words
  def hey words
    if empty_or_nil? words
      'Fine. Be that way!'
    elsif shouting? words
      'Woah, chill out!'
    elsif asking_question? words
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Bob < Teenager
end
