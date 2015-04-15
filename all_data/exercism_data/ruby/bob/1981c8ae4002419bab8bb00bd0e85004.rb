class Bob
  def hey(greeted_with)
    sentence = Sentence.new(greeted_with)
    if sentence.silence?
      "Fine. Be that way!"
    elsif sentence.asking?
      "Sure."
    elsif sentence.shouting?
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

end

class Sentence
  attr_accessor :contents

  def initialize(contents)
    @contents = contents
  end

  def silence?
    @contents.strip.empty?
  end

  def shouting?
    @contents.upcase == @contents && @contents.downcase != @contents
  end

  def asking?
    @contents.end_with?('?') && !shouting?
  end
end
