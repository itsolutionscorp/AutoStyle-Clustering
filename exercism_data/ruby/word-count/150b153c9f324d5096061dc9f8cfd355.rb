class Words
  attr_reader :words

  def initialize(input)
    @words = input.downcase.gsub(/\W/," ").split
  end

  def count
    words.inject(Hash.new(0)) do |counter_hash, word| 
      counter_hash[word] += 1 ; counter_hash 
    end
  end
end
