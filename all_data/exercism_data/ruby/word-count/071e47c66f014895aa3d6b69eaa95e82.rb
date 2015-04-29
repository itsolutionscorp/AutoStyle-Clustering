class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end 

  def word_count
    arr = @phrase.split(/[^a-zA-Z_'0-9]+/)
    count = Hash.new(0)

    arr.each do |elt|
      count[elt] += 1
    end

    count
  end
  
end
