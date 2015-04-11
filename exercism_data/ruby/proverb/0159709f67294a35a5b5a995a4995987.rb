class Proverb
  def initialize(*chain, qualifier: "")
    @chain = chain
    @last_rhyme = (qualifier + " " + @chain[0]).strip
  end
  
  def to_s
    proverb = ""
    (0...@chain.length-1).each do |i|
      proverb += "For want of a #{@chain[i]} the #{@chain[i+1]} was lost.\n"
    end
    proverb += "And all for the want of a #{@last_rhyme}."
  end
end
