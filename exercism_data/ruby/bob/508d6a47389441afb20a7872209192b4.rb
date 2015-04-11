class Bob
  Reaction = Struct.new(:answer, :matcher)

  REACTIONS = [
    Reaction.new('Fine. Be that way!', ->(s){ s.strip.empty?   }),
    Reaction.new('Woah, chill out!',   ->(s){ s.upcase == s    }),
    Reaction.new('Sure.',              ->(s){ s.end_with?('?') }),
    Reaction.new('Whatever.',          ->(_){ true             })
  ]

  def hey(sentence)
    reaction = REACTIONS.find{ |reaction| reaction.matcher[sentence] }
    reaction.answer if reaction
  end
end
