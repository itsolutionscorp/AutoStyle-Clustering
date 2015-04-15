class Bob
  def hey(utterance)
    BobResponder.new(utterance).respond
  end
end

class BobResponder
  def initialize(utterance)
    @utterance = utterance
  end

  def respond
    if silence?
      'Fine. Be that way!'
    elsif being_yelled_at?
      'Woah, chill out!'
    elsif being_questioned?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?
    @utterance =~ /\A *\z/
  end

  def being_yelled_at?
    !@utterance.empty? && @utterance.upcase == @utterance
  end

  def being_questioned?
    @utterance.end_with?('?')
  end
end
