class Phrase
  attr_accessor :sentence

  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    arr = @sentence.scan(/[\w']+/).map {|n| n.downcase}
    hash = Hash.new(0)
    (0...arr.size).each do |n|
      hash[arr[n]] += 1
    end
    hash
  end
end
