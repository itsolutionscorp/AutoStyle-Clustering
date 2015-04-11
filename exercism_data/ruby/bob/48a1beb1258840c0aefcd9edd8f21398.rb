class Teenager
  def hey words
    word = Words.new(words)
    if word.silent?
      'Fine. Be that way!'
    elsif word.shouting?
      'Woah, chill out!'
    elsif word.asking_question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Bob < Teenager
end

class Words
  def initialize(words)
    @words = words
  end

  def shouting? 
    @words == @words.upcase && !@words.empty?
  end
  def asking_question? 
    @words.end_with?('?')
  end
  def silent? 
    @words.to_s == ''
  end
end
