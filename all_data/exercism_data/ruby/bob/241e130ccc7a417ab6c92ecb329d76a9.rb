class Bob
  def hey(text)
    analyzed_text = TextAnalyzer.new(text)
    if analyzed_text.is_mute?
      'Fine. Be that way!'
    elsif analyzed_text.is_screaming?
      'Woah, chill out!'
    elsif analyzed_text.is_question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class TextAnalyzer
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def is_mute?
    striped_text.empty?
  end

  def is_screaming?
    text.upcase.eql?(text)
  end

  def is_question?
    text.end_with?('?')
  end

  private

  def striped_text
    @text.to_s.strip
  end
end
