class Phrase < Struct.new(:phrase)
  def word_count
    phrase.downcase.scan(/[\w']+/).inject(Hash.new(0)) { |h, w| h[w] += 1; h }
  end
end
