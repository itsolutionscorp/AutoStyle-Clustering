require 'delegate'

class Statement < SimpleDelegator
  def initialize(prompt)
    @prompt = prompt
  end

  def is_silence?
    @prompt.strip.empty?
  end

  def is_shouting?
    !is_silence? and @prompt.upcase == @prompt and @prompt.downcase != @prompt
  end

  def is_question?
    !is_shouting? and @prompt.end_with?('?')
  end
end

class Bob
  def hey(prompt)
    annoyance = Statement.new(prompt)
    case
      when annoyance.is_silence? then 'Fine. Be that way!'
      when annoyance.is_shouting? then 'Woah, chill out!'
      when annoyance.is_question? then 'Sure.'
      else 'Whatever.'
    end
  end
end
