class Words

  attr_reader :string
  def initialize(string)
    @string = string.downcase.gsub(/\W/, ' ').split(' ')
  end

  def count
    @string.each_with_object({}) do |word, hash|
      hash[word] = @string.count(word)
    end
  end
end     
