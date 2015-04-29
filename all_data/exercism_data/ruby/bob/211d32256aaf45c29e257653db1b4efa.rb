class Bob

  RESPONSES_TO = Hash.new("Whatever.").merge!(
    yelling: "Whoa, chill out!",
    whisper: "What?",
    question: "Sure.",
    silence: "Fine. Be that way!"
  )

  def hey(remark)
    tone_of_voice = ToneOfVoice.analyze(remark)
    RESPONSES_TO[tone_of_voice]
  end

end

class ToneOfVoice

  TONES = {
    yelling:  -> (tone) { tone.remark.match(/[A-Z]+/) && !tone.remark.match(/[a-z]+/) },
    question: -> (tone) { tone.remark.end_with?('?') },
    silence:  -> (tone) { tone.remark.strip.empty? },
    whisper:  -> (tone) { !tone.remark.match(/[A-Z]+/) && tone.remark.match(/[a-z]+/) },
  }

  attr_reader :remark

  def initialize(remark)
    @remark = remark
  end

  def self.analyze(remark)
    new(remark).analysis
  end

  def analysis
    tone = TONES.detect{ |_, lambda| lambda.call(self) } || [:other]
    tone.first
  end

end
