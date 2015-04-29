class Sentence

  def passive_aggressive? input
    input.match(/^\s*$/)
  end

  def yelling? input
    input == input.upcase
  end

  def questioning? input
    input.match(/.*\?$/)
  end
end


class Bob

  def initialize
    @sentence = Sentence.new
  end

  def hey input

    input = input.to_s

    if @sentence.passive_aggressive?(input) then "Fine. Be that way!"
    elsif @sentence.yelling?(input) then  "Woah, chill out!"
    elsif @sentence.questioning?(input) then "Sure."
    else "Whatever."
    end

  end

end
