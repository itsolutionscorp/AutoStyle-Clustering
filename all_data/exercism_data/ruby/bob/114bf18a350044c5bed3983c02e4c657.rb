class Bob
  def hey(sentence)
    phrase = Phrase.new(sentence)
    case phrase.type
    when 'empty'
      'Fine. Be that way.'
    when 'yelled'
      'Woah, chill out!'
    when 'tell'
      'Whatever.'
    else
      'Sure.'
    end
  end
end


class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def type
    if empty?
      'empty'
    elsif yelled?
      'yelled'
    elsif tell?
      'tell'
    else # question
      'question'
    end
  end

protected
  def empty?
    @phrase.nil? || @phrase.empty?
  end

  def yelled?
    @phrase.upcase == @phrase
  end

  def tell?
    @phrase.end_with?('.', '!')
  end
end
