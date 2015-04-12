class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    wc = Hash.new
    words = @phrase.split(/[\s,]/)
    words.each do |w|
      w.downcase!
      next unless /[[:alnum:]]+/.match(w)
      w.gsub!(/[^[[:alnum:]]|']/, '')
      if wc.has_key?(w)
        wc[w] += 1
      else
        wc[w] = 1
      end
    end
    wc
  end



end
