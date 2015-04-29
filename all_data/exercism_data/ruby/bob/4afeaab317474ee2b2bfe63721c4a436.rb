class Bob
  Response = {
    silence: 'Fine. Be that way!',
    yelling: 'Whoa, chill out!',
    asking: 'Sure.',
    statement: 'Whatever.'
  }
  Silence = ->(t) { t.gsub(/\s/, '').empty? }
  Asking = ->(t) { t =~ /\?\z/ }
  Yelling = ->(t) { t == t.upcase && t =~ /[A-Z]/ }

  def hey(text)
    Response[statement_style(text)]
  end

  def statement_style(text)
    case text
    when Yelling
      :yelling
    when Silence
      :silence
    when Asking
      :asking
    else
      :statement
    end
  end
end
