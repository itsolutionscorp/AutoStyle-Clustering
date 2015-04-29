class Bob
  RESPONSES = { 
    shout: 'Woah, chill out!', 
    question: 'Sure.', 
    silent: 'Fine. Be that way!',
    statement: 'Whatever.' 
  }.freeze

  def hey remark
    RESPONSES.fetch Remark.new(remark).type
  end
end

class Remark
  def initialize remark
    @remark = remark.to_s
  end

  def type
    return :silent if silent?
    return :shout if shout?
    return :question if question?
    :statement
  end

  private
  def shout?
    @remark.upcase == @remark
  end

  def question?
    @remark.end_with? '?' 
  end

  def silent?
    @remark.empty?
  end
end
