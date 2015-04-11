class Phrase < String
  def word_count
    counts = Hash.new(0)
    self.gsub(/[^\w ]/, ' ').split.each do |word|
      counts[word.downcase] += 1
    end
    counts
  end  
end
