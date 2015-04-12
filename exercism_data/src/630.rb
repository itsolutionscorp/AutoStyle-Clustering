class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    out = {}
    p = @phrase.downcase.gsub(/[^a-z0-9,\s]/,'')
    p.split(/[\s|,]/).each do |w|
      if not w.empty?
        if out[w].nil?
          out[w] = 1
        else
          out[w] = out[w] + 1
        end
      end
    end
    out
  end

end
