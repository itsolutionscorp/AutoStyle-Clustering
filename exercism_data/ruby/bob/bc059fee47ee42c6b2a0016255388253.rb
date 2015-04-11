class Speech
  attr_reader :source

  def initialize(source)
    @source = source
  end

  def silence?
    source.nil? or source.strip.empty?
  end

  def shouting?
    source == source.upcase
  end

  def question?
    source.end_with? '?'
  end

  def classify
    if silence?
      :silence
    elsif shouting?
      :shouting
    elsif question?
      :question
    else
      :unknown
    end
  end

end

class Bob

  def hey(speech)
    case Speech.new(speech).classify
      when :silence then 'Fine. Be that way!'
      when :shouting then 'Woah, chill out!'
      when :question then 'Sure.'
      else 'Whatever.'
    end
  end

end
