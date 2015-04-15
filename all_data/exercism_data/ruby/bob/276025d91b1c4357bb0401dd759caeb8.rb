class Bob
  def hey(prompt)
    feeling = Feeling.new(prompt)

    if feeling.ignored?
      'Fine. Be that way!'
    elsif feeling.berated?
      'Woah, chill out!'
    elsif feeling.interrogated?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Feeling
  attr_reader :prompt

  def initialize(prompt)
    @prompt = prompt
  end

  def ignored?
    prompt.strip.empty?
  end

  def berated?
    prompt =~ /[A-Z]/ && prompt.upcase == prompt
  end

  def interrogated?
    prompt.end_with?('?')
  end
end
