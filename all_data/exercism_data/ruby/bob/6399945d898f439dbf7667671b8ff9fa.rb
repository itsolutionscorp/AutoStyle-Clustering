class Bob
  def hey(utterance)
    respond Utterance.new(utterance)
  end

  private

  def respond(utterance)
    case
    when utterance.absent?
      'Fine. Be that way!'
    when utterance.shouted?
      'Woah, chill out!'
    when utterance.asked?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

Utterance = Struct.new(:utterance) do
  def absent?
    utterance.strip == ''
  end

  def shouted?
    utterance =~ /[A-Z]/ && utterance == utterance.upcase
  end

  def asked?
    utterance.end_with? '?'
  end
end
