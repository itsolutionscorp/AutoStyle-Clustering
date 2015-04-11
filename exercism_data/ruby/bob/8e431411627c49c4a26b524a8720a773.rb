class Bob
  attr_reader :response, :tone

  def initialize
    @response = Response.new
    @tone = Tone.new
  end

  def hey(statement)
    tone.comment = statement

    if tone.silent?
      response.annoyed
    elsif tone.angry?
      response.surprised
    elsif tone.question?
      response.indifferent
    else
      response.common
    end
  end
end

class Tone
  attr_accessor :comment

  def silent?
    comment.to_s.strip.empty?
  end

  def angry?
    comment.upcase == comment
  end

  def question?
    comment.end_with?('?')
  end
end

class Response
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
