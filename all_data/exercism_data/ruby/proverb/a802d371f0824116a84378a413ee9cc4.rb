class Proverb

  def initialize *chain, qualifier: nil
    @lines = chain.each_with_index.collect do |word, i|
      if second_word = chain[i+1]
        "For want of a %s the %s was lost." % [word, second_word]
      else
        "And all for the want of a %s%s." % [qualifier && "#{qualifier} ", chain.first]
      end
    end
  end

  def to_s
    @lines.join "\n"
  end

end
