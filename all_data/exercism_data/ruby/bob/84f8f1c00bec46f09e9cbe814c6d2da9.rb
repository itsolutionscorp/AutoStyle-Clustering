class Bob
  def hey(phrase)
    case phrase
      when Phrase::Silence then 'Fine. Be that way!'
      when Phrase::Shouting then 'Woah, chill out!'
      when Phrase::Question then 'Sure.'
      else 'Whatever.'
    end
  end
end

class Phrase
  Silence = ->(input) { input.to_s.strip.empty? }
  Shouting = ->(input) { input.upcase == input }
  Question = ->(input) { input.end_with?('?') }
end
