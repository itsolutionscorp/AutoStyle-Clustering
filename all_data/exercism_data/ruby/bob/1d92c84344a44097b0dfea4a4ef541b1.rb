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

  attr_reader :remark

  def initialize(remark)
    @remark = remark
  end

  def self.analyze(remark)
    new(remark).analysis
  end

  def analysis
    tone = TONES.detect{ |tone| send("is_#{tone}?") }
    tone ? tone.to_sym : :other
  end

  def is_question?
    remark.end_with?('?')
  end

  def is_yelling?
    has_upper_case_letters? && !has_lower_case_letters?
  end

  def is_whisper?
    !has_upper_case_letters? && has_lower_case_letters?
  end

  def is_silence?
    remark.strip.empty?
  end

  private

  def has_upper_case_letters?
    remark.match(/[A-Z]+/)
  end

  def has_lower_case_letters?
    remark.match(/[a-z]+/)
  end
end
