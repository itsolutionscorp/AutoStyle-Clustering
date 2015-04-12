class Phrase
  def initialize(word)
    @w = word
  end

  def word_count
    w = @w.gsub(/[,]/, ' ')
    w = w.gsub(/[^a-zA-Z0-9 ']/, '')
    w.split(' ').inject({}) do |d, w|
      k = w.downcase
      if d[k].nil?
        d[k] = 0
      end
      d[k] = d[k] + 1
      d
    end
  end
end
