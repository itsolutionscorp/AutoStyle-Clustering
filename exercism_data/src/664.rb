class Phrase
  def initialize(string)
    @string = string
  end
  def word_count
    counts = {}
    for i in @string.downcase.scan(/[\w']+/)
      if counts.has_key?(i)
        counts[i] += 1
      else
        counts[i] = 1
      end
    end
    return counts
  end
end
