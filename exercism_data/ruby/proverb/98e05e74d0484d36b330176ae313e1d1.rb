class Proverb
  @@chain
  @@qualifier
  def initialize(*chain)
  	(chain.last.is_a? Hash) ? (@@qualifier = chain.pop) : @@qualifier = {}
    @@chain = chain
  end

  def to_s()
  	proverb = ""
  	(0..@@chain.length - 2).each do |index|
  		proverb += "For want of a #{@@chain[index]} the #{@@chain[index + 1]} was lost.\n"
  	end
  	proverb += "And all for the want of a %s%s." % [(@@qualifier == {}) ? "" : @@qualifier[:qualifier] + " " , @@chain[0]]
  end
end
