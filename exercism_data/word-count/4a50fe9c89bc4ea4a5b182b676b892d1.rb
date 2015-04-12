class Phrase < Struct.new(:phrase)
  def word_count
    phrase.
      downcase.
      scan(/[a-z0-9']+/).
      inject(Hash.new{ |h,k| h[k]=0 }) do |h, w| 
        h[w] += 1
        h
      end
  end
end
