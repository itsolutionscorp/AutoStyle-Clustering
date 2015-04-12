class Phrase

  def initialize(text)
    @input = text
  end

  def word_count
    word_clusters.each_with_object({}) do |(word, cluster), counts|
      counts[word] = cluster.size
    end
  end

  private

  def tokenize(input)
    input.downcase.split(/\W+/)
  end

  def word_clusters
    tokenize(@input).group_by{ |word| word }
  end

end
