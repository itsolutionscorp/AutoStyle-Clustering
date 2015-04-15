class Phrase
  def initialize phrase
    @phrase = phrase.gsub(/[:!&@$%^&,.]/, " ").downcase.split
  end
  def word_count
    @phrase.each_with_object(Hash.new 0) { |w, c| c[w] += 1 }
  end
end
