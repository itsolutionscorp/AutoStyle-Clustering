class Bob

  def hey(utterance)
    hear(utterance)
    think
    speak
  end

  private

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

  def silence; /\A\s*\z/     end
  def yelling; /\A[^a-z]+\z/ end
  def asking;  /\?\z/        end

  def speak
    @response
  end

end
