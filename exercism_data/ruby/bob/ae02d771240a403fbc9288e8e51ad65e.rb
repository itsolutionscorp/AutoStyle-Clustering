class StringTypeAnalyzer
  def initialize str
    @str = str.to_s
  end

  def silence? 
    @str.strip.empty?
  end

  def shout? 
    @str.upcase == @str
  end

  def question? 
	  @str.end_with? '?'
  end
end


class Bob
  def hey str
    bobs_response = StringTypeAnalyzer.new(str)

    return 'Fine. Be that way!' if bobs_response.silence?
    return 'Woah, chill out!' if bobs_response.shout?
    return 'Sure.' if bobs_response.question?
    return 'Whatever.'
  end
end
