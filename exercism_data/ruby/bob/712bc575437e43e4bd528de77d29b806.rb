class Bob
  def hey(s)
    prompt = Prompt.new(s)
    if prompt.empty?
      "Fine. Be that way!"
    elsif prompt.yelling?
      "Woah, chill out!"
    elsif prompt.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Prompt
  attr_reader :prompt

  def initialize(prompt)
    @prompt = prompt
  end

  def empty?
    prompt.strip.empty?
  end

  def yelling?
    prompt =~ /[a-zA-Z]/ && prompt.upcase == prompt
  end

  def question?
    prompt.end_with? "?"
  end
end
