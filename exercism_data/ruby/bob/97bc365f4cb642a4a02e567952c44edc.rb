class Bob
  def input() @input end

  def last_character
    input.slice -1
  end

  def saying_nothing?
    not input or input.empty?
  end

  def yelling?
    not input.match /[a-z]/
  end

  def question?
    '?' == last_character
  end

  def telling?
    %w( . ! ).include? last_character
  end
  
  def hey input
    @input = input
    if saying_nothing? then "Fine. Be that way!"
    elsif yelling? then "Woah, chill out!"
    elsif question? then "Sure."
    elsif telling? then "Whatever."
    else "unknown"
    end
  end
end
