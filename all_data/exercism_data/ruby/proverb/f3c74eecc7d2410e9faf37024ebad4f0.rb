class Proverb

  def initialize(wordA, wordB)
    @proverb_hash = {:wordA => :wordB}
  end

  def proverb
    @proverb_hash.each do |key, value|
      puts "For want of a #{key} the #{value} was lost\n"+
          "And all for the want of a nail."
    end

  end

  def self.proverb(wordA, wordB)
    new(wordA, wordB).proverb
  end
end
