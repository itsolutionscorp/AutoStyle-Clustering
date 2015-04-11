class Bob
  def hey(talk)
    case
      when talk.match(/[A-Z]/) && talk == talk.upcase then 'Woah, chill out!'
      when talk.end_with?('?') then 'Sure.'
      when talk.match(/\A[[:space:]]*\Z/) then 'Fine. Be that way!'
      else 'Whatever.'
    end
  end
end
