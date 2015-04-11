class DNA
  def initialize(strand)
    @strand = strand
  end

  def length
    @strand.length
  end

  def trait(position)
    @strand[position]
  end

  def has_trait?(position)
    trait(position) != nil
  end
end

class Hamming
  class << self
    def compute(parent_strand, child_strand)
      parent, child = DNA.new(parent_strand), DNA.new(child_strand)  

      mutations = 0
      n         = 0

      while child.has_trait?(n) && parent.has_trait?(n)
        mutations += 1 if parent.trait(n) != child.trait(n)  
        n += 1
      end

      mutations
    end
  end
end
