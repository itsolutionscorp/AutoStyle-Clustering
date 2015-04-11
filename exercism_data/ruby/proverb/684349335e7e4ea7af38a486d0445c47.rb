class Proverb

  attr_reader :chain, :qualifier, :proverb
  def initialize(*chain, qualifier: nil)
    @chain = chain
    @qualifier = qualifier
    build_proverb
  end

  def to_s
    proverb
  end

  def build_proverb
    @proverb =  ce_pairs.reduce("") {|txt,ce_pair| txt << build_line(ce_pair)}
    @proverb << "And all for the want of a #{qualifier_string}#{first_cause}."
  end

  def ce_pairs
    chain.each_cons(2).map {|ce_pair| ce_pair }
  end

  def build_line(ce_pair)
    "For want of a #{ce_pair.first} the #{ce_pair.last} was lost.\n"
  end

  def qualifier_string
    qualifier ? "#{qualifier} " : ""
  end

  def first_cause
    chain.first
  end
end
