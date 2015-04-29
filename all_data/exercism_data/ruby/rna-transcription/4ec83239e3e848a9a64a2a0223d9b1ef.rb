require 'delegate'

# Delegate to String class to avoid inheritance
# See: http://words.steveklabnik.com/beware-subclassing-ruby-core-classes
class StringDelegator < SimpleDelegator
  def initialize(string = '')
    super(string.to_str)
  end

  def inspect
    "<#{self.class}: #{super}>"
  end
end

# A string constructed form the alphabet A, T, G, C.
class DNA < StringDelegator
  def to_rna
    RNA.new(tr('T', 'U'))
  end
end

# A string constructed form the alphabet A, U, G, C.
class RNA < StringDelegator
  def to_dna
    DNA.new(tr('U', 'T'))
  end
end
