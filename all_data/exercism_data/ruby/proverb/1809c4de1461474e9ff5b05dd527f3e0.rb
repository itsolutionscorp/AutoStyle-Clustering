class Proverb
  def initialize(*args, qualifier: nil)
    @nouns = *args
    @small_noun = args.first
    @qualifier = qualifier
  end

  def to_s
    verse
  end

  def verse
    @verse ||= rhyme + kicker
  end

  private
  def rhyme
    @nouns.each_cons(2).reduce('') {|acc, (small, big)| acc << build_line(small, big)}
  end

  def build_line(small, big)
    "For want of a #{small} the #{big} was lost.\n"
  end

  def kicker
    "And all for the want of a #{@qualifier} #{@small_noun}.".squeeze ' '
  end
end
