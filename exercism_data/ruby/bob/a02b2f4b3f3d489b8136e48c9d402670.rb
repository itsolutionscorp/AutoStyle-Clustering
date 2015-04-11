class Bob
  def hey(the_sentence)
    the_sentence = QualifiedSentence.new the_sentence

    if the_sentence.exclamatory?
      'Woah, chill out!'
    elsif the_sentence.interrogative?
      'Sure.'
    elsif the_sentence.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class QualifiedSentence
  def initialize(contents)
    @contents = contents
  end

  def interrogative?
    @contents.strip.end_with? '?'
  end

  def exclamatory?
    @contents == @contents.upcase && contains_alphas?
  end

  def contains_alphas?
    ! @contents[/[a-zA-Z]+/].nil?
  end

  def empty?
    @contents.strip.length == 0
  end
end
