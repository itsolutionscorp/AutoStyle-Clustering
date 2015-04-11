class Bob
  def hey(sentance)
    Discussion.new(sentance).lets_talk
  end

end

class Discussion

  def initialize(sentance)
    @sentance = sentance
  end

  def lets_talk
    return 'Fine. Be that way!' if nothing_to_say?
    return 'Woah, chill out!' if yell?
    return 'Sure.' if question?
    return 'Whatever.'
  end

  def yell?
    @sentance == @sentance.upcase
  end

  def question?
    @sentance.end_with?('?')
  end

  def nothing_to_say?
    @sentance.strip.size.zero?
  end
end
