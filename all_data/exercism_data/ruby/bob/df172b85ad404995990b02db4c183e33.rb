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
    @contents.strip == ""
  end

  def shouting?
    @contents.match(/\p{Lu}/m) && !@contents.match(/\p{Ll}/m)
  end

  def asking?
    @contents.match(/\?\Z/m) && !shouting?
  end
end
