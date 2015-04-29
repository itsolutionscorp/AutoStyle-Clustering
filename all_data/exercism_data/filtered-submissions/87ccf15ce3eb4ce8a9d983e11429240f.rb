def compute strand, other_strand
    strand_contents = [strand, other_strand].map(&:chars)
    shorter, longer = strand_contents.sort_by(&:length)
    shorter.zip(longer).count { |a, b| a != b }