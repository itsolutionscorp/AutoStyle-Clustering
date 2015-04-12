class Phrase
  def initialize(string)
    @arr = string.downcase.gsub(/[^[[:word:]]['-]]/, ' ').split
  end
  
  def word_count
    countHash = {}
    @arr.uniq.each do |e|
      countHash[e] = @arr.count(e)
    end
    countHash
  end
end
