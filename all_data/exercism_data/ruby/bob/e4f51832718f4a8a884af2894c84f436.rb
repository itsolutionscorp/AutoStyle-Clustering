class Bob
  def initialize
  end

  def hey(talk)
    case
      when talk.match(/[A-Z]/) && talk == talk.upcase then 'Woah, chill out!'
      when talk.match(/\?\Z/) then 'Sure.'
      when talk.match(/\A[[:space:]]*\Z/) then 'Fine. Be that way!'
      else 'Whatever.'
    end
  end
end
