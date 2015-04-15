class Bob

  ANSWERS = {
      :nothing => 'Fine. Be that way!',
      :yelled => 'Woah, chill out!',
      :question => 'Sure.',
      :default => 'Whatever.'
  }

  def hey(words)
    self::class.ANSWERS[ConversationAnalyzer.new(words).type]
  end

end

class ConversationAnalyzer

  def initialize(sentence)
    @sentence = sentence.to_s
  end

  def type
    return :nothing if @sentence.strip.empty?
    return :yelled if @sentence == @sentence.upcase
    return :question if @sentence.end_with?('?')
    return :default
  end

end
