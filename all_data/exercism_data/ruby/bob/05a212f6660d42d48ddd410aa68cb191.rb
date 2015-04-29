class Bob
  ANSWERS = {
    statement:   'Whatever.',
    question:    'Sure.',
    exclamation: 'Whoa, chill out!',
    silence:     'Fine. Be that way!'
  }

  SAFE_WORDS = %w(ok dmv)

  def hey(phrase)
    ANSWERS[type phrase]
  end

  private

  def type(phrase)
    ANSWERS.keys.reduce { |a, e| send(e.to_s + '?', phrase) ? e : a }
  end

  def clean(phrase)
    SAFE_WORDS.reduce(phrase) { |a, e| a.gsub(/#{e}/i, '') }
  end

  def question?(phrase)
    clean(phrase) =~ /\?\Z/
  end

  def exclamation?(phrase)
    clean(phrase) =~ /[A-Z]{2,}/
  end

  def silence?(phrase)
    phrase.strip.empty?
  end
end
