class Anagram
  def initialize spartacus
    @spartacus = spartacus
  end

  def match doppelgangers
    doppelgangers.select { |clone| clone?(clone) }
  end

  private

  def clone?(person)
    dna_spartacus[/\A#{dna(person)}\z/i]
  end

  def dna_spartacus
    @dna_spartacus ||= dna(@spartacus)
  end

  def dna person
    person.chars.sort { |a, b| a.downcase <=> b.downcase }.join
  end
end
