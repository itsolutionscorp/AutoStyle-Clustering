class Bob

  attr_reader :silence, :yelling, :asking

  def initialize
    @silence = ->(s) { s.to_s == "" }
    @yelling = ->(s) { s !~ /[a-z]/ }
    @asking  = ->(s) { s =~ /\?\z/ }
  end

  def hey(utterance)
    hear(utterance)
    think
    speak
  end

  def hear(utterance)
    @utterance = utterance
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
