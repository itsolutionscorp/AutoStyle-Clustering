class Bob
  def hey what_someone_said
    phrase_to_respond_to = Phrase.new what_someone_said

    if phrase_to_respond_to.nothing?
      'Fine. Be that way?'
    elsif phrase_to_respond_to.yelling? 
      'Woah, chill out!'
    elsif phrase_to_respond_to.asking? 
      'Sure.'
    else 
      'Whatever.'
    end
  end
end

class Phrase
  def initialize phrase_to_analyze
    @phrase_to_analyze = phrase_to_analyze.to_s
  end

  def nothing?
    phrase_to_analyze.to_s.empty?
  end

  def yelling?
    phrase_to_analyze.upcase == phrase_to_analyze
  end

  def asking?
    phrase_to_analyze.end_with? '?'
  end

  private
  def phrase_to_analyze
    @phrase_to_analyze
  end
end
