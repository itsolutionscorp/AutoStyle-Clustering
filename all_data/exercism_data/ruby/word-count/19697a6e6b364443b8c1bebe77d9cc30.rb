class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    word_clusters.each_with_object(Hash.new) do |tuple, hash|
      hash[tuple.first] = tuple.last.count
    end
  end

  def word_clusters
    @input.downcase.scan(/[[:word:]]+/).group_by{ |word| word}
  end
end
