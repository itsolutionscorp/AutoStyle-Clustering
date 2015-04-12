class Phrase

  attr_accessor :phrase

  def initialize(phrase)
    @phrase = phrase.gsub(/[^0-9a-z ,']/i, '')
  end

  def word_count
    words, hash = @phrase.split(/[\s|,]/).reject {|i| i.empty?}, {}

    words.each do |w|
      w = w.downcase
      if hash.has_key?(w)
        hash[w] += 1
      else
        hash[w] = 1
      end
    end
    hash
  end

end
