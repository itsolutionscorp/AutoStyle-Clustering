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

  def question?
    @words[-1] == '?'
  end

  def yelling?
    return false if @words.gsub(/\?$/, '').to_i.to_s == @words.gsub(/\?$/, '')
    return false if @words.gsub(/[\d,\s]/, '').empty?
    @words.upcase == @words
  end

  def anything?
    @words.gsub(/\s/, '').empty?
  end
end
