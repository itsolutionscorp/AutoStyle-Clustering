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

class Sentence < Struct.new(:content)

  def silent?
    content.nil? || content.strip.empty?
  end

  def yelling?
    content.upcase == content
  end

  def asking?
    content.end_with? '?'
  end

end
