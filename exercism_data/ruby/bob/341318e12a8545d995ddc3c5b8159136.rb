class Bob
  def hey(content)
    sentence = Sentence.new(content)

    if sentence.silent?
      'Fine. Be that way!'
    elsif sentence.yelling?
      'Woah, chill out!'
    elsif sentence.asking?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

Sentence = Struct.new(:content) do
  def silent?
    content.to_s.strip.empty?
  end

  def yelling?
    content.upcase == content
  end

  def asking?
    content.end_with? '?'
  end
end
