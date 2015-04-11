class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    @words.
      downcase.                    # canonicalize the string
      split(/\W+/).                # split on non-word characters
      reduce(Hash.new(0)) do |results, elem|
        results[elem] += 1
        results
      end
  end
end
