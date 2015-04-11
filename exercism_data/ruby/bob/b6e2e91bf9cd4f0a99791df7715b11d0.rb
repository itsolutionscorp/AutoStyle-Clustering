class Bob
  def hey(something)
    @something = something

    if ignored?
      'Fine. Be that way!'
    elsif berated?
      'Woah, chill out!'
    elsif interrogated?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def ignored?
    @something.strip.empty?
  end

  def berated?
    @something.match(/[A-Z]/) && @something.upcase == @something
  end

  def interrogated?
    @something.end_with?('?')
  end
end
