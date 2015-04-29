class DNA
  def initialize(dna) @dna = dna end

  def nucleotide_counts
    DEFAULT.merge \
      chars.group_by(&:to_s).map(&COUNT).reduce(&:merge) || {}
  end

  def count thing
    raise ArgumentError unless KNOWN.include? thing 
    super
  end
  KNOWN = %w[ A T C G U ]
  ODD   = ['U']
  DEFAULT = (KNOWN - ODD).map { |x| { x => 0 }}.reduce(&:merge)
  COUNT = -> (( k, v )) {{ k => v.count }}

  # unsafe delegator:
  def method_missing(*a) @dna.send *a end
end
