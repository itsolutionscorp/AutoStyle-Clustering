class Anagram
  attr_reader :inngangsord

  def initialize(ordet)
    @inngangsord = ordet
  end
  
  def match(ordliste)
    ordliste.select do |ordet|
      ordet if samme_storrelse(ordet) && samme_bokstavene(ordet)
    end
  end

  def samme_storrelse(ordet)
    ordet.size == inngangsord.size
  end

  def samme_bokstavene(ordet)
    ordet.chars.sort == inngangsord.chars.sort
  end
end
