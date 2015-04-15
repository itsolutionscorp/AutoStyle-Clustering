class Statement
  def initialize(prompt)
    @prompt = prompt
  end

  def isSilence?
    @prompt.strip == ''
  end

  def isShouting?
    !isSilence? and @prompt.upcase == @prompt and @prompt.downcase != @prompt
  end

  def isQuestion?
    !isShouting? and @prompt[-1] == '?'
  end
end

class Bob
  def hey(prompt)
    annoyance = Statement.new(prompt)
    case
      when annoyance.isSilence? then 'Fine. Be that way!'
      when annoyance.isShouting? then 'Woah, chill out!'
      when annoyance.isQuestion? then 'Sure.'
      else 'Whatever.'
    end
  end
end
