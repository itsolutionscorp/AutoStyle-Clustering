class Sentence

  def initialize input
    @input = input.to_s
  end

  def passive_aggressive?
    @input.match(/^\s*$/)
  end

  def yelling?
    @input == @input.upcase
  end

  def questioning?
    @input.match(/.*\?$/)
  end

end


class Bob

  def hey input

    sentence = Sentence.new input

    case
    when sentence.passive_aggressive? then "Fine. Be that way!"
    when  sentence.yelling? then  "Woah, chill out!"
    when sentence.questioning? then "Sure."
    else "Whatever."
    end

  end

end
