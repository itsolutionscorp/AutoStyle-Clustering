class Bob

  def initialize
    @silence = /\A\s*\z/
    @yelling = /\A[^a-z]+\z/
    @asking  = /\?\z/
  end

  def hey(utterance)
    hear(utterance)
    think
    speak
  end

  private

  attr_reader :silence, :yelling, :asking

  def hear(utterance)
    @utterance = utterance || ""
  end

  def think
    @response = case @utterance
      when silence ; "Fine. Be that way!"
      when yelling ; "Woah, chill out!"
      when asking  ; "Sure."
      else           "Whatever."
      end
  end

  def speak
    @response
  end

end
