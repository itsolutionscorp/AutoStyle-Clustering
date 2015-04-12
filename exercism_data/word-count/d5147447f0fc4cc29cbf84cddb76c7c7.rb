class Phrase
  def initialize(word_collection)
    @word_collection = word_collection.downcase.gsub(/[:!&@$%^,]/, " ")
    @word_array = @word_collection.chomp.split(" ")
  end

  def word_count
    word_hash = Hash.new(0)

    @word_array.each{|word| 
      word_hash[word] += 1
    }

    word_hash
  end
end
