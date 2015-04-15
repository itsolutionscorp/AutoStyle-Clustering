class House
  def initialize
  end

  def verses(*args)
    @output = ''
    number  = args[0]

    while number <= args[1] do
      @output += verse(number)
      @output += "\n"
      number  += 1
    end

    @output
  end

  def verse(arg)
    @output = ''
    intro
      pairs = { 1  => {noun: "in the house", verb: "Jack built"},
                2  => {noun: "malt", verb: "lay"},
                3  => {noun: "rat", verb:"ate"},
                4  => {noun: "cat", verb:"killed"},
                5  => {noun: "dog", verb:"worried"},
                6  => {noun: "cow with the crumpled horn", verb:"tossed"},
                7  => {noun: "maiden all forlorn", verb:"milked"},
                8  => {noun: "man all tattered and torn", verb:"kissed"},
                9  => {noun: "priest all shaven and shorn", verb:"married"},
                10 => {noun: "rooster that crowed in the morn", verb:"woke"},
                11 => {noun: "farmer sowing his corn", verb:"kept"},
                12 => {noun: "horse and the hound and the horn", verb:"belonged to"}
      }
      if arg == 1
      else
       verse = arg 
          verse = verse.to_i
          while verse > 1
            verb = pairs[verse][:verb]
            noun = pairs[verse][:noun]
            construct(noun, verb)
            verse -= 1
          end
        end

    arg == 1 ? outro(first = true) : outro
    @output
  end

  private

  def intro
    @output << "This is "
  end

  def outro(first = false)
    if first == true
      @output << "the house that Jack built.\n"
    else
    @output << "in the house that Jack built.\n"
    end
  end

  def construct(noun, verb)
    @output << "the #{noun} that #{verb} "
  end
end
