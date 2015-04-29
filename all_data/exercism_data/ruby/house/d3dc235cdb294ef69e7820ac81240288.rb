class House
  START = "This is"
  CONCLUSION = " the house that Jack built.\n"
  STAGES = [
    ['malt', 'lay in'],
    ['rat', 'ate'],
    ['cat', 'killed'],
    ['dog', 'worried'],
    ['cow with the crumpled horn', 'tossed'],
    ['maiden all forlorn', 'milked'],
    ['man all tattered and torn', 'kissed'],
    ['priest all shaven and shorn', 'married'],
    ['rooster that crowed in the morn', 'woke'],
    ['farmer sowing his corn', 'kept'],
    ['horse and the hound and the horn', 'belonged to']
  ]

  def self.recite
    (0..STAGES.size).map {|num| stanza(num) }.join("\n")
  end

  def self.stanza(num)
    middle = STAGES[0...num].reverse.map{|stage| line(stage)}.join
    START + middle + CONCLUSION
  end

  def self.line(stage)
    " the #{stage[0]}\nthat #{stage[1]}"
  end
end
