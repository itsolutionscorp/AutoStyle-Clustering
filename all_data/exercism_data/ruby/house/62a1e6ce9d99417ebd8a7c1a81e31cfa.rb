class House
  LINES = [
    {thing: 'house that Jack built.'},
    {thing: 'malt', action: 'lay in'},
    {thing: 'rat', action: 'ate'},
    {thing: 'cat', action: 'killed'},
    {thing: 'dog', action: 'worried'},
    {thing: 'cow with the crumpled horn', action: 'tossed'},
    {thing: 'maiden all forlorn', action: 'milked'},
    {thing: 'man all tattered and torn', action: 'kissed'},
    {thing: 'priest all shaven and shorn', action: 'married'},
    {thing: 'rooster that crowed in the morn', action: 'woke'},
    {thing: 'farmer sowing his corn', action: 'kept'},
    {thing: 'horse and the hound and the horn', action: 'belonged to'},
  ]
  class << self
    def recite(verse=12)
      1.upto(verse).map { |n| verse(n) }.join("\n")
    end

    def verse(num)
      [first_line(num), rest_of_verse(num)].join
    end

    private

    def first_line(num)
      "This is the #{ LINES[num-1][:thing] }\n"
    end

    def rest_of_verse(num)
      return unless LINES[num-1][:action]
      next_line = "that #{LINES[num-1][:action]} the #{LINES[num-2][:thing]}\n"
      [next_line, rest_of_verse(num-1)].join
    end
  end
end

puts House.recite
