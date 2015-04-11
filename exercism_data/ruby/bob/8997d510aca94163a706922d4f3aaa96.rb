class Bob
  def hey(words)
    @words = words
    respond_to_speaker
  end

  private

  def respond_to_speaker
    silence || shout || question || default
  end

  def silence
    "Fine. Be that way!" if @words.strip.empty?
  end

  def question
    "Sure." if @words.end_with?("?")
  end

  def shout
    "Woah, chill out!" if @words.upcase == @words
  end

  def default
    "Whatever."
  end
end
