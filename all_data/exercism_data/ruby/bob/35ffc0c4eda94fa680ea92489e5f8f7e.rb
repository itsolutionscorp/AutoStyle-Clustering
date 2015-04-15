class Bob
  def hey(adress)
    if adress.upcase == adress && adress =~ /[a-zA-Z]/
      reply(:yelling)
    elsif adress =~ /\?\z/
      reply(:question)
    elsif adress =~ /\A\s*\z/
      reply(:silence)
    else
      reply(:default)
    end
  end

  def reply(interaction)
    replies = {
      yelling: 'Woah, chill out!',
      question: 'Sure.',
      silence: 'Fine. Be that way!',
      default: 'Whatever.'
    }
    replies[interaction]
  end
end
