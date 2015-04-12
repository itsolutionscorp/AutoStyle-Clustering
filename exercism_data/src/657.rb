class Phrase

  def initialize(sentence)
    @split = sentence.split
  end
  
  def word_count
    count = Hash.new 0
    @split.each do |w|
      count[w] += 1
    end
    return count
  end

end
