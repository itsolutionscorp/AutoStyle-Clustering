class Bob
  def hey(the_sentence)
    the_sentence = QualifiedSentence.new the_sentence

    response = 'Sure.' if the_sentence.is_a_question?
    response = 'Woah, chill out!' if the_sentence.is_being_yelled?
    response = 'Fine. Be that way!' if the_sentence.is_empty?
    response ||= 'Whatever.'
    response
  end
end

class QualifiedSentence
  def initialize(contents)
    @contents = contents
  end

  def is_a_question?
    @contents.strip.split(//).last == '?'
  end

  def is_being_yelled?
    @contents == @contents.upcase && contains_alphas?
  end

  def contains_alphas?
    ! @contents[/[a-zA-Z]+/].nil?
  end

  def is_empty?
    @contents.strip.length == 0
  end
end
