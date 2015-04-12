# Write a program that given a phrase can count the occurrences of each word in that phrase.

# For example for the input `"olly olly in come free"`

# ```plain
# olly: 2
# in: 1
# come: 1
# free: 1
# ```

class Phrase
  
  attr_reader :str

  def initialize(str)
    @str = str
  end

  # def word_count
  #   str.split(" ").group_by {|word| str.count(word) }  
  # end
  def word_count
    words = str.downcase.gsub(/[^a-z\d\s']/i, " ").split(" ")
    words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end
end
