class Bob

  def hey string
    sentence = Sentence.new(string)
    if sentence.empty?
      "Fine. Be that way!"
    elsif sentence.shouted?
      "Woah, chill out!"
    elsif sentence.questioned?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Sentence < Struct.new(:sentence)

  def empty?
    sentence.strip.empty?
  end

  def questioned?
    sentence.end_with? "?"
  end

  def shouted?
    sentence.upcase == sentence and not sentence.downcase == sentence
  end

end
