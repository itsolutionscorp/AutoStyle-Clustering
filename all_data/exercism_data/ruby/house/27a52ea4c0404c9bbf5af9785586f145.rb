class House
  attr_reader :prefix, :phrases, :template
  def initialize
    @prefix = "This is"
    @phrases = [
                ["house", "Jack built."],
                ["malt", "lay in"],
                ["rat", "ate"],
                ["cat", "killed"],
                ["dog", "worried"],
                ["cow with the crumpled horn", "tossed"],
                ["maiden all forlorn", "milked"],
                ["man all tattered and torn", "kissed"],
                ["priest all shaven and shorn", "married"],
                ["rooster that crowed in the morn", "woke"],
                ["farmer sowing his corn", "kept"],
                ["horse and the hound and the horn", "belonged to"]
               ]
    @template = " the %s that %s"
  end

  def verses(first, last)
    (first..last).inject('') {|res, num| res << verse(num) << "\n" }
  end

  def verse(num)
    (num-1).downto(0).inject(prefix) {|vrs,i| vrs += phrase(i) } << "\n"
  end

  def phrase(n)
    template % [phrases[n].first, phrases[n].last]
  end
end
