class Bob
  def hey(words)
    case Sentence.new(words).tonality
    when :silence then silence_response
    when :yelling then heated_response
    when :question then confirming_response
    else normal_response
    end
  end

  private

  def silence_response
    'Fine. Be that way.'
  end

  def heated_response
    'Woah, chill out!' 
  end

  def confirming_response
    'Sure.' 
  end

  def normal_response
    "Whatever."
  end
end

class Sentence
  def initialize(words)
    @words = words.to_s
  end

  def tonality
    return :silence if silence?
    return :yelling if yelling?
    return :question if question?
    :normal
  end

  def silence?
    @words.empty?
  end

  def yelling?
    @words == @words.upcase
  end

  def question?
    @words.end_with? '?'
  end
end
