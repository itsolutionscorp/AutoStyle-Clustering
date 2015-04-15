class House

  def initialize
    @verse = ["the horse and the hound and the horn that belonged to",
              "the farmer sowing his corn that kept","the rooster that crowed in the morn that woke",
              "the priest all shaven and shorn that married",
              "the man all tattered and torn that kissed", "the maiden all forlorn that milked",
              "the cow with the crumpled horn that tossed","the dog that worried",
              "the cat that killed", "the rat that ate", "the malt that lay in",
              "the house that Jack built"].reverse
  end

  def verse num
    "This is #{combine num}.\n"
  end

  def verses first, second
    str = ""
    (first..second).each do |cnt|
      str << verse(cnt) << "\n"
    end
    str
  end

  def combine num
    @verse[0,num].reverse.reduce { |acc, words| acc <<  " #{words}"}
  end

end
