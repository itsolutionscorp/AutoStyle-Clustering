class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    Hash[word_clusters.map { |word, frequency| [word, frequency.count] }]
  end

  def word_clusters
    @input.downcase.scan(/[[:word:]]+/).group_by { |word| word }
  end
end
