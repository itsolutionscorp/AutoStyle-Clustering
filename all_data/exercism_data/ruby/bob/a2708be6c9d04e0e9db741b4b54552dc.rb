class Bob
  def hey(message)
    utterance = Utterance.new(message)

    if utterance.silent?
      'Fine. Be that way!'
    elsif utterance.shouting?
      'Whoa, chill out!'
    elsif utterance.asking?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Utterance
  attr_reader :content

  def initialize(content)
    @content = content
  end

  def silent?
    content.strip == ''
  end

  def shouting?
    content.upcase == content && content.downcase != content
  end

  def asking?
    content.end_with?('?')
  end
end
