class Bob
  SHOUTING = -> utterance { utterance.upcase == utterance }
  TACITURN = -> utterance { utterance.to_s.strip.empty? }
  QUESTION = -> utterance { utterance.end_with? '?' }

  def hey(utterance)
    case utterance
    when TACITURN
     'Fine. Be that way!'
    when SHOUTING
     'Woah, chill out!'
    when QUESTION
      'Sure.'
    else
      'Whatever.'
    end
  end
end
