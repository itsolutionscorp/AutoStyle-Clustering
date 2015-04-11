class Bob
  def hey(phrase)
    @phrase = phrase

    case
    when yelling
      'Woah, chill out!'
    when questions
      'Sure.'
    when silence
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def yelling
    @phrase == @phrase.upcase && @phrase != @phrase.downcase
  end

  def questions
    @phrase.split('').last == '?'
  end

  def silence
    @phrase.strip.empty?
  end
end
