class Bob
  def hey input
    @input = input
    if saying_nothing? then "Fine. Be that way!"
    elsif yelling? then "Woah, chill out!"
    elsif question? then "Sure."
    elsif telling? then "Whatever."
    end
  end

  private

  def input() @input end

  def saying_nothing?
    not input or input.empty?
  end

  def yelling?
    input.upcase == input
  end

  def question?
    input.end_with? '?'
  end

  def telling?
    input.end_with? '.', '!'
  end
end
