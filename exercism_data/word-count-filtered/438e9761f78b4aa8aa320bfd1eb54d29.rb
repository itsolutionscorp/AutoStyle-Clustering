class Phrase

  def initialize(phrase)
    @words = phrase.nil? ? {} : phrase.split(/[\s,]+/)
  end

  def word_count
    count = Hash.new(0)
    @words.each do |word|
      word.delete!("!&@$%^&:") 
      count[word.downcase] += 1 unless word.empty?
    end
    count
  end

end
