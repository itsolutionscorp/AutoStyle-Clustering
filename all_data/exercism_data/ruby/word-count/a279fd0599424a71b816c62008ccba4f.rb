class Words

  def initialize(words)
    @words = words
  end

  def count
    separated_words = Hash.new(0)
    @words.split(" ").each do |word|
      separated_words[word.downcase.gsub(/\W/, "")] += 1
    end
    separated_words.delete_if {|k,v| k.empty? }
  end

end
