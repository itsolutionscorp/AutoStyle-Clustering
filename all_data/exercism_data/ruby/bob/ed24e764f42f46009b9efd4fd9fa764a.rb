class Bob
  attr_reader :tone
  private :tone

  def hey(statement)
    tone = Tone.new(statement)

    if tone.silent?
      Response.annoyed
    elsif tone.angry?
      Response.surprised
    elsif tone.question?
      Response.indifferent
    else
      Response.common
    end
  end
end

class Tone
  def initialize(comment)
    @comment = comment
  end

  def silent?
    @comment.to_s.strip.empty?
  end

  def angry?
    @comment.upcase == @comment
  end

  def question?
    @comment.end_with?('?')
  end
end

module Response
  module_function

  def annoyed
    'Fine. Be that way!'
  end

  def surprised
    'Woah, chill out!'
  end

  def indifferent
    'Sure.'
  end

  def common
    'Whatever.'
  end
end
