class Phrase < String
  
  def word_count
    words = self.split /\W/
    words.reject! {|w| w.empty? }
    words.map(&:downcase).inject({}) do |counts, word|
      counts[word] = 1 + counts[word].to_i
      counts
    end
  end
    
end
