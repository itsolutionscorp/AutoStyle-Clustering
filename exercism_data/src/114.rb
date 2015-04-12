class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    phrase_hsh = {}
    phrase_arr = @phrase.downcase
                        .split(/[^a-zA-Z0-9(')('')]+/)

    phrase_arr.uniq.each do |word|
      phrase_hsh.merge!({ word => phrase_arr.count(word)})
    end

    phrase_hsh
  end
end
