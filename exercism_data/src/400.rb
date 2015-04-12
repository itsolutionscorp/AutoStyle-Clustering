class Phrase
  def initialize (line)
    @line = line
  end
  def word_count
    @line.split(/[^A-Za-z1-9']/).map(&:downcase).reduce({}){|d, w|
      d[w] = (d[w] || 0) + 1 if w.size > 0
      d
    }
  end
end
