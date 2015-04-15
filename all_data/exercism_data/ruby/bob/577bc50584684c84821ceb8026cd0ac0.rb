class Bob
  def hey(phrase)
    @phrase = phrase

    case
    when yelling?
      'Woah, chill out!'
    when question?
      'Sure.'
    when silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def yelling?
    is_upcased? && !string_of_numbers?
  end

  def question?
    @phrase.split('').last == '?'
  end

  def silence?
    @phrase.strip.empty?
  end

  def is_downcased?
    @phrase == @phrase.downcase
  end

  def is_upcased?
    @phrase == @phrase.upcase
  end

  def string_of_numbers?
    is_upcased? && is_downcased?
  end
end
