class Bob

  RESPONSES_TO = {
    yelling: "Whoa, chill out!",
    whisper: "What?",
    question: "Sure.",
    silence: "Fine. Be that way!",
    default: "Whatever."
  }

  def hey(remark)
    @remark = remark
    respond
  end

  private

  def respond
    tone_of_voice = ToneOfVoice.analyze(@remark)
    RESPONSES_TO[tone_of_voice] || RESPONSES_TO[:default]
  end

end

class ToneOfVoice
  TONES = %w{yelling question whisper silence}

  def initialize(remark)
    @remark = remark
  end

  def self.analyze(remark)
    new(remark).analysis
  end

  def analysis
    tone = TONES.detect{ |tone| send("#{tone}?") }
    tone ? tone.to_sym : :other
  end

  def question?
    @remark[-1] == '?'
  end

  def yelling?
    has_upper_case_letters? && !has_lower_case_letters?
  end

  def whisper?
    !has_upper_case_letters? && has_lower_case_letters?
  end

  def silence?
    @remark.strip.empty?
  end

  private

  def has_upper_case_letters?
    @remark.match(/[A-Z]+/)
  end

  def has_lower_case_letters?
    @remark.match(/[a-z]+/)
  end
end
