class Bob
  def hey(words)
    @words = words
    if anything?
      "Fine. Be that way!"
    elsif yelling?
      "Woah, chill out!"
    elsif question?
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def question?
    @words[-1] == '?'
  end

  def yelling?
    return false unless has_letters?
    @words.upcase == @words
  end

  def anything?
    @words.gsub(/\s/, '').empty?
  end

  def has_letters?
    !@words.gsub(/[\d,?\s]/, '').empty?
  end
end
